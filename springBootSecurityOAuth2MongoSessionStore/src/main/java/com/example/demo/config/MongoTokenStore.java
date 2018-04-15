package com.example.demo.config;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;


@Component
public class MongoTokenStore implements TokenStore, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(MongoTokenStore.class);

    private static String AUTHENTICATION_OBJECT_FIELD = "authentication_key";
    private static String ACCESS_TOKEN_VALUE_FIELD = "access_token_value";
    private static String ACCESS_TOKEN_OBJECT_FIELD = "access_token_object";
    private static String REFRESH_TOKEN_VALUE_FIELD = "refresh_token_value";
    private static String REFRESH_TOKEN_OBJECT_FIELD = "refresh_token_object";
    private static final String CLIENT_ID_FIELD = "client_id";
    private static final String USER_FIELD = "user";
    private static String CREATED_FIELD = "created";


    @Value("${app.oauth.mongo-token-store-dbname}") String dbName;
    @Value("${app.oauth.mongo-token-store-accesstoken-collection-name}") String accessTokenCollectionName;
    @Value("${app.oauth.mongo-token-store-refreshtoken-collection-name}") String refreshTokenCollectionName;
    @Value("${app.oauth.mongo-token-store-clear-tokens-on-boot}") boolean clearTokensOnBoot;

    @Autowired
    MongoClient mongoClient;

    AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    MongoDatabase mongoDb;

    MongoCollection<Document> accessTokenCollection;
    MongoCollection<Document> refreshTokenCollection;

    @Override
    public void afterPropertiesSet() {
        mongoDb = mongoClient.getDatabase(this.dbName);

        if (mongoDb.listCollections().filter(new Document("name", accessTokenCollectionName)).first() == null) {
            mongoDb.createCollection(accessTokenCollectionName);
        }
        if (mongoDb.listCollections().filter(new Document("name", refreshTokenCollectionName)).first() == null) {
            mongoDb.createCollection(refreshTokenCollectionName);
        }

        accessTokenCollection = mongoDb.getCollection(accessTokenCollectionName);
        refreshTokenCollection = mongoDb.getCollection(refreshTokenCollectionName);

        IndexOptions accessTokenIndexOptions = new IndexOptions().unique(true);
        accessTokenCollection.createIndex(Indexes.ascending(ACCESS_TOKEN_VALUE_FIELD), accessTokenIndexOptions);
        IndexOptions refreshTokenIndexOptions = new IndexOptions().unique(true);
        refreshTokenCollection.createIndex(Indexes.ascending(REFRESH_TOKEN_VALUE_FIELD), refreshTokenIndexOptions);

//        if (clearTokensOnBoot) {  // TODO: implement
//            accessTokenCollection.drop();
//            refreshTokenCollection.drop();
//        }

    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());

    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(ACCESS_TOKEN_VALUE_FIELD, token);

        Document at = accessTokenCollection.find(dbObject).first();
        if (!Objects.isNull(at)) {
            Binary authenticationSerialized = (Binary) at.get(AUTHENTICATION_OBJECT_FIELD);
            return (OAuth2Authentication) deserialize(authenticationSerialized.getData());
        } else {
            return null;
        }
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        byte[] authenticationSerialized = serialize(authentication);
        byte[] tokenSerialized = serialize(token);

        Document doc = new Document();
        doc.put(AUTHENTICATION_OBJECT_FIELD, authenticationSerialized);
        doc.put(ACCESS_TOKEN_OBJECT_FIELD, tokenSerialized);
        doc.put(ACCESS_TOKEN_VALUE_FIELD, token.getValue());
        doc.put(CLIENT_ID_FIELD, authentication.getOAuth2Request().getClientId());
        doc.put(USER_FIELD, authentication.getName());
        doc.put(CREATED_FIELD, LocalDateTime.now().toString());

        accessTokenCollection.insertOne(doc);  // TODO: replace if exists?
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(ACCESS_TOKEN_VALUE_FIELD, tokenValue);
        Document at = accessTokenCollection.find(dbObject).first();
        if (!Objects.isNull(at)) {
            Binary accessTokenSerialized = (Binary) at.get(ACCESS_TOKEN_OBJECT_FIELD);
            return (OAuth2AccessToken) deserialize(accessTokenSerialized.getData());
        } else {
            return null;
        }
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(ACCESS_TOKEN_VALUE_FIELD, token.getValue());
        accessTokenCollection.deleteOne(dbObject);
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        byte[] authenticationSerialized = serialize(authentication);
        byte[] refreshTokenSerialized = serialize(refreshToken);

        Document doc = new Document();
        doc.put(REFRESH_TOKEN_OBJECT_FIELD, refreshTokenSerialized);
        doc.put(REFRESH_TOKEN_VALUE_FIELD, refreshToken.getValue());
        doc.put(CLIENT_ID_FIELD, authentication.getOAuth2Request().getClientId());
        doc.put(USER_FIELD, authentication.getName());
        doc.put(CREATED_FIELD, LocalDateTime.now().toString());
        doc.put(AUTHENTICATION_OBJECT_FIELD, authenticationSerialized);

        refreshTokenCollection.insertOne(doc);  // TODO: replace if exists?
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(REFRESH_TOKEN_VALUE_FIELD, tokenValue);
        Document rt = refreshTokenCollection.find(dbObject).first();
        if (!Objects.isNull(rt)) {
            Binary refreshTokenSerialized = (Binary) rt.get(REFRESH_TOKEN_OBJECT_FIELD);
            return (OAuth2RefreshToken) deserialize(refreshTokenSerialized.getData());
        } else {
            return null;
        }
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(REFRESH_TOKEN_VALUE_FIELD, token);

        Document rt = refreshTokenCollection.find(dbObject).first();
        if (!Objects.isNull(rt)) {
            Binary authenticationSerialized = (Binary) rt.get(AUTHENTICATION_OBJECT_FIELD);
            return (OAuth2Authentication) deserialize(authenticationSerialized.getData());
        } else {
            return null;
        }
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put(REFRESH_TOKEN_VALUE_FIELD, token.getValue());
        refreshTokenCollection.deleteOne(dbObject);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        // TODO: implement
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;

        String key = authenticationKeyGenerator.extractKey(authentication);

        BasicDBObject search = new BasicDBObject();
        search.put(AUTHENTICATION_OBJECT_FIELD, key);
        Document accessTokenDocument = accessTokenCollection.find(search).first();

        if (Objects.isNull(accessTokenDocument)) {
            LOG.info("Authentication object not found in database for " + authentication.getName());
        } else {
            Object token = accessTokenDocument.get(ACCESS_TOKEN_OBJECT_FIELD);
            accessToken = (OAuth2AccessToken) deserialize((byte[]) token);
        }

        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        // TODO: implement
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        // TODO: implement
        return null;
    }

    private byte[] serialize(Object object) {
        return SerializationUtils.serialize(object);
    }

    private Object deserialize(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }
}
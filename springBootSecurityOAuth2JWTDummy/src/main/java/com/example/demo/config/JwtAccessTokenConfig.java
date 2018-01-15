package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;

/**
 * Beans to define the JWT access token source
 */
@Configuration
public class JwtAccessTokenConfig {

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();   // default
//        final CustomJwtAccessTokenConverter accessTokenConverter = new CustomJwtAccessTokenConverter();

        // set custom converter
//        accessTokenConverter.setAccessTokenConverter(new AccessTokenConverter() {
//            public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
//                return new CustomJwtAccessTokenConverter().convertAccessToken(token, authentication);
//            }
//
//            @Override
//            public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
//                return new CustomJwtAccessTokenConverter().extractAccessToken(value, map);
//            }
//
//            @Override
//            public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
//                OAuth2Authentication o  = new CustomJwtAccessTokenConverter().extractAuthentication(map);
//                return o;
//            }
//        });


        accessTokenConverter.setSigningKey("12345");
//        accessTokenConverter.setVerifierKey("54321");   // asymmetric key
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
        return new JwtTokenStore(accessTokenConverter());    // find token here at request interceptor
    }
}

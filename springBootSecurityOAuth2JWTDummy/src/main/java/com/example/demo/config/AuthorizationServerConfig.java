package com.example.demo.config;

import java.security.KeyPair;
import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Value("${security.jwt.client-id}")
//    private String clientId;
//
//    @Value("${security.jwt.client-secret}")
//    private String clientSecret;
//
//    @Value("${security.jwt.grant-type}")
//    private String grantType;
//
//    @Value("${security.jwt.scope-read}")
//    private String scopeRead;
//
//    @Value("${security.jwt.scope-write}")
//    private String scopeWrite = "write";
//
//    @Value("${security.jwt.resource-ids}")
//    private String resourceIds;

//    @Autowired
//    private TokenStore tokenStore;

//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
////        Assert.notNull(keystoreConfig.getKeyStorePass(),
////                "You must provide a keystore password for your '"
////                        + keystoreConfig.getPath() + "' keystore");
//
//        JwtAccessTokenConverter converter = new CustomTokenEnhancer();
////        KeyPair keyPair = new KeyStoreKeyFactory(
////                keystoreConfig.getResource(), keystoreConfig
////                .getKeyStorePass().toCharArray()).getKeyPair(
////                keystoreConfig.getKeyAlias(), keystoreConfig.getKeyPass()
////                        .toCharArray());
//        converter.setKeyPair(keyPair);
//
//        return converter;
//    }

    @Autowired
    TokenStore tokenStore;

    @Autowired
    UserDetailsService userDetailsService;


    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    ClientDetailsService clientDetailsService;

    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
//                .withClientDetails(clientDetailsService)
                .inMemory()
                .withClient("clientId111")
                .secret("clientSecret111")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .autoApprove("read", "write")
                .accessTokenValiditySeconds(6000000)
                .refreshTokenValiditySeconds(30000000)
        ;
//                .resourceIds("resourceIds111");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
//        endpoints.tokenStore(tokenStore)
//                .accessTokenConverter(accessTokenConverter)
//                .tokenEnhancer(enhancerChain)
//                .authenticationManager(authenticationManager);

        endpoints
                .tokenStore(tokenStore)   // TODO: remove
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter)     // generate JwtToken at login
                ;
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
////        oauthServer.allowFormAuthenticationForClients().realm("xx/yy");
////        oauthServer.auth
//    }

}


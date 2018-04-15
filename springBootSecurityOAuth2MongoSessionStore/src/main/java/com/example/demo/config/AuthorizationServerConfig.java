package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    MongoTokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
//                .withClientDetails(clientDetailsService)
                .inMemory() // app client
                .withClient("clientId111")
                .secret("clientSecret111")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .autoApprove("read", "write")
                .accessTokenValiditySeconds(6000000)
                .refreshTokenValiditySeconds(30000000)
                ;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)   // TODO: remove
                .authenticationManager(authenticationManager)
                ;
    }
}


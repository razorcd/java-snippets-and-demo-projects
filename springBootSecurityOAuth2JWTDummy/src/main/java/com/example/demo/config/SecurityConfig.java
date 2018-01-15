//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
//@Configuration
////@EnableWebSecurity
//@EnableOAuth2Sso
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/publicpage").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successForwardUrl("/privatepage").permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
//                .and()
//                .csrf().disable();
//    }
//
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
////        builder.inMemoryAuthentication()
////                .withUser("user").password("pass123").roles("ANONYMOUS", "USER")
////            .and()
////                .withUser("admin").password("pass123").roles("ANONYMOUS", "USER", "ADMIN");
////    }
//}
//
//
//
////
////package com.ubitricity.conectivitymanager.configuraton.security;
////
////        import org.springframework.beans.factory.annotation.Autowired;
////        import org.springframework.context.annotation.Bean;
////        import org.springframework.context.annotation.Configuration;
////        import org.springframework.security.authentication.AuthenticationManager;
////        import org.springframework.security.core.userdetails.UserDetailsService;
////        import org.springframework.security.oauth2.common.OAuth2AccessToken;
////        import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
////        import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
////        import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
////        import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
////        import org.springframework.security.oauth2.provider.ClientDetailsService;
////        import org.springframework.security.oauth2.provider.OAuth2Authentication;
////        import org.springframework.security.oauth2.provider.token.TokenEnhancer;
////        import org.springframework.security.oauth2.provider.token.TokenStore;
////        import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
////        import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
////        import org.springframework.web.servlet.HandlerInterceptor;
////        import org.springframework.web.servlet.ModelAndView;
////        import org.springframework.web.servlet.mvc.WebContentInterceptor;
////
////        import javax.servlet.http.HttpServletRequest;
////        import javax.servlet.http.HttpServletResponse;
////
////@Configuration
////@EnableAuthorizationServer
////public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
////
////    @Autowired
////    private TokenStore tokenStore;
////
////    @Autowired
////    CustomUserDetailsService customUserDetailsService;
////
////    @Autowired
////    ClientDetailsService clientDetailsService;
////
////    @Autowired
////    public AuthenticationManager authenticationManager;
////
////    @Bean
////    public JwtAccessTokenConverter accessTokenConverter() {
////        final JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
////        accessTokenConverter.setSigningKey("12345");
//////        accessTokenConverter.setVerifierKey("54321");   // TODO: find encrypt algorithm
////        return accessTokenConverter;
////    }
////
////    @Bean
////    public TokenStore tokenStore() {
////        return new JwtTokenStore(accessTokenConverter());
////    }
////
////    @Override
////    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
////        configurer
//////                .withClientDetails(clientDetailsService)
////                .inMemory()
////                .withClient("clientId111")
////                .secret("clientSecret111")
////                .authorizedGrantTypes("password", "refresh_token")
////                .scopes("read", "write")
////                .autoApprove("read", "write")
////                .accessTokenValiditySeconds(600000)
////                .refreshTokenValiditySeconds(3000000)
//////                .resourceIds("resourceIds111")
////        ;
////    }
////
////    @Override
////    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        endpoints
////                .tokenStore(tokenStore())
////                .authenticationManager(authenticationManager)
////                .userDetailsService(customUserDetailsService)
////                .accessTokenConverter(accessTokenConverter());
////    }
////
////}
////

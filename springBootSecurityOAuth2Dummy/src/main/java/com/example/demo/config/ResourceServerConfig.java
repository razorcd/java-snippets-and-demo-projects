package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
//    private UserDetailsService userDetailsService;
    private CustomUserDetailsService userDetailsService;

//    @Autowired
//    private TokenExtractor tokenExtractor;

//    public ResourceServerConfig() {
//        super();
//    }



    @Bean
    AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

//    @Bean
//    TokenExtractor tokenExtractor() {
//        TokenExtractor t = new TokenExtractor() {
//            @Override
//            public Authentication extract(HttpServletRequest request) {
//                return null;
//            }
//        };
//        return t;
//    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenExtractor(new TokenExtractor() {
//            @Override
//            public Authentication extract(HttpServletRequest request) {
////                return new UsernamePasswordAuthenticationToken("principal111111", "cred222");
//
//                String tokenValue = extractToken(request);
//                if (tokenValue != null) {
//                    PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(tokenValue, "");
//                    return authentication;
//                }
//                return null;
//
//
//            };
//
//
//
//            // TODO: rest can be inherited
//            protected String extractToken(HttpServletRequest request) {
//                // first check the header...
//                String token = extractHeaderToken(request);
//
//                // bearer type allows a request parameter as well
//                if (token == null) {
//                    token = request.getParameter(OAuth2AccessToken.ACCESS_TOKEN);
//                    if (token == null) {
//                    }
//                    else {
//                        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE, OAuth2AccessToken.BEARER_TYPE);
//                    }
//                }
//
//                return token;
//            }
//
//            /**
//             * Extract the OAuth bearer token from a header.
//             *
//             * @param request The request.
//             * @return The token, or null if no OAuth authorization header was supplied.
//             */
//            protected String extractHeaderToken(HttpServletRequest request) {
//                Enumeration<String> headers = request.getHeaders("Authorization");
//                while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
//                    String value = headers.nextElement();
//                    if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
//                        String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
//                        // Add this here for the auth details later. Would be better to change the signature of this method.
//                        request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,
//                                value.substring(0, OAuth2AccessToken.BEARER_TYPE.length()).trim());
//                        int commaIndex = authHeaderValue.indexOf(',');
//                        if (commaIndex > 0) {
//                            authHeaderValue = authHeaderValue.substring(0, commaIndex);
//                        }
//                        return authHeaderValue;
//                    }
//                }
//
//                return null;
//            }
//        });
//    }




    //    @Autowired
//    private ResourceServerTokenServices tokenServices;
//
//    @Value("${security.jwt.resource-ids}")
//    private String resourceIds;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId(resourceIds).tokenServices(tokenServices);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//            .requestMatchers()
//            .and()
//            .authorizeRequests()
//            .antMatchers("/public/**").permitAll()
//            .antMatchers("/simple", "/simple/**" ).authenticated();
//    }
}

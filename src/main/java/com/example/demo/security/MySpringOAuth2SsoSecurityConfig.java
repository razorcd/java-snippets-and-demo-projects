package com.example.demo.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@EnableOAuth2Sso
public class MySpringOAuth2SsoSecurityConfig extends WebSecurityConfigurerAdapter {

    @RequestMapping("/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    // Spring Security confuguration
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//		super.configure(http);

        http
            .authorizeRequests().antMatchers("/").permitAll()
            .and()
            .authorizeRequests().antMatchers("/h2/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .headers().frameOptions().sameOrigin()
            .httpStrictTransportSecurity().disable()
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/")
            ;
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/h2**");
//        web.ignoring().anyRequest();
//    }
}
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @RequestMapping("/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(HttpMethod.GET, "/", "/anonymous").permitAll()
            .and()
                .authorizeRequests().anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/login").permitAll()
            .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("pass123").roles("USER")
            .and()
                .withUser("admin").password("pass123").roles("ADMIN", "USER");

    }


}

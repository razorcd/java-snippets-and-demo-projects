package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/publicpage").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successForwardUrl("/privatepage").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("user").password("pass123").roles("ANONYMOUS", "USER")
            .and()
                .withUser("admin").password("pass123").roles("ANONYMOUS", "USER", "ADMIN");
    }
}

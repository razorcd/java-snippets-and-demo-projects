//package com.example.demo.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity  // TODO: enable this type of security
//@Configuration
//public class MySpringWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    // Spring Security configuration
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////		super.configure(http);
//
//        // basic auth
////		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//
//        // form auth
//        http.authorizeRequests().anyRequest().authenticated()
//        .and()
//          .formLogin().loginPage("/myLogin").permitAll()
//        .and()
//          .logout().permitAll();
//
//    }
//
//
//
//}

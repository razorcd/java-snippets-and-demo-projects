package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @RequestMapping("/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }


    @Bean
    public CustomAuthenticationFilter authenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler();
//        filter.setAuthenticationFailureHandler(new ForwardAuthenticationFailureHandler("/login?error"));
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers(HttpMethod.GET, "/", "/anonymous", "/me", "/logMeInAsUser", "/login").hasRole("ANONYMOUS")
            .and()
                .authorizeRequests().anyRequest().authenticated()
//            .and()
//                .formLogin().loginPage("/login").successForwardUrl("/").permitAll()   //because we are using our own filter
            .and()
                .addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class)
                .logout().permitAll();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.inMemoryAuthentication()
//                .withUser("user").password("pass123").roles("ANONYMOUS", "USER")
//            .and()
//                .withUser("admin").password("pass123").roles("ANONYMOUS", "USER", "ADMIN");
//
//    }


}

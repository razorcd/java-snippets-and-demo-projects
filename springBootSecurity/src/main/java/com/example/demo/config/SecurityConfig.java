package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessor;
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
                .requiresChannel().antMatchers("*").requiresSecure()  //  requires HTTPS with generated KEY in keystore (see application.yml)
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            .and()
                .sessionManagement().sessionFixation().none() // so when switching between HTTP and HTTPS or user tries to login again. Here Spring does not recreate the Session object (no new key)
            .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/", "/anonymous", "/logMeInAsUser", "/login").hasRole("ANONYMOUS")  // or .hasAuthority("ANONYMOUS") or .hasAnyRole("ROLE1", "ROLE2"...)
            .and()
                .authorizeRequests().antMatchers("/me", "/me2", "/prefilter", "/postfilter").permitAll()  // .access("principal.username == 'user@example.com'")  // strict condition
            .and()
                .authorizeRequests().anyRequest().authenticated()
//            .and()
//                .formLogin().loginPage("/login").successForwardUrl("/").permitAll()   //because we are using our own filter
            .and()
                .addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class)
                .logout().permitAll()
            .and()
                .csrf().disable();


            //remember me configuration
            http.rememberMe()
                    .rememberMeCookieName("remember-me-cookie-name")
            .and()
                 .rememberMe()
                    .key("rem-me-key")
                    .rememberMeParameter("remember-me-param")
                    .rememberMeCookieName("my-remember-me")
                    .tokenValiditySeconds(86400);
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

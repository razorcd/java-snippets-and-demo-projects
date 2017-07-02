package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet Filter  inserted in the  Filter Proxy Chain
// More details:    http://leaks.wanari.com/2017/11/28/how-to-make-custom-usernamepasswordauthenticationfilter-with-spring-security/
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = super.obtainUsername(request);
        String password = super.obtainPassword(request);
        int age = Integer.parseInt(request.getParameter("age"));

        CustomAuthenticationToken token = new CustomAuthenticationToken(username, password, age);

        super.setDetails(request, token);

        // calls the Security Auth manager with this new token
        return this.getAuthenticationManager().authenticate(token);
    }
}

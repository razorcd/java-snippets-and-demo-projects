package com.example.demo.config;

import com.example.demo.persistance.ClientEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private int age;

    public CustomAuthenticationToken(String principal, String credentials, int age) {
        super(principal, credentials);
        this.age = age;
    }

//    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, int age) {
    public CustomAuthenticationToken(ClientEntity principal, String credentials, Collection<? extends GrantedAuthority> authorities, int age) {
        super(principal, credentials, authorities);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

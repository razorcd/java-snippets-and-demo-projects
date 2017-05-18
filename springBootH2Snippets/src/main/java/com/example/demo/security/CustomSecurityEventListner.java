package com.example.demo.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 * Will subscribe to Authentification Failure Events from Spring Security.
 */
@Component
public class CustomSecurityEventListner implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        System.out.println(" ! Security event: " + event.getException().getMessage());
    }
}

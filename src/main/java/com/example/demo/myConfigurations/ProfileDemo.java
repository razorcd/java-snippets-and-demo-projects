package com.example.demo.myConfigurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileDemo {

    @Bean(name = "profileMessage")
    @Profile("!development") //any other profile
    public String someMethod(@Value("${my.message}") String message) {
        return message + ", in Other Profile";
    }

    @Bean(name = "profileMessage")
    @Profile("development")
    public String someMethodDev(String message) {
        return "Harcoded value in Development Profile";
    }
}

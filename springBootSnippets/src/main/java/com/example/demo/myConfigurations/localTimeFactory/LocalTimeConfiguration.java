package com.example.demo.myConfigurations.localTimeFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class LocalTimeConfiguration {

    // bean will be a singleton by default
    @Bean
    public LocalTimeFactory localTimeFacotry() {
        return new LocalTimeFactory();
    }

    @PostConstruct
    public void postConst() {
        System.out.println("localTimeFacotry Bean1: " + localTimeFacotry());
        System.out.println("localTimeFacotry Bean2: " + localTimeFacotry());
    }

}

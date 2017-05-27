package com.example.demo.myConfigurations;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class MyExitCodeConfig {

    @PreDestroy
    public void preExit() {
        System.out.println("! Exit code running.");
    }
}

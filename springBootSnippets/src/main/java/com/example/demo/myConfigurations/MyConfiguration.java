package com.example.demo.myConfigurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Value("${randomvalue}")
    String randomPropValue;

    @Bean(name = "rnd")
    public String randomPropValueConf() {
        return randomPropValue;
    }

}

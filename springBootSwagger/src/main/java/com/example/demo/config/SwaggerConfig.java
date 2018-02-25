package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket theApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
//                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());

    }


    private ApiInfo metaData() {
        return  new ApiInfo(
                "Spring Boot app title",
                "Spring Boot app description",
                "1.0",
                "Terms of service",
                new Contact("name here", "https://myWebsite", "email@example.com"),
                "MIT",
                "http://urlHere");
    }
}
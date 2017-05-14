package com.example.demo.myConfigurations;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Overwrites default Spring classes that create properties.
 */
@Configuration
public class MyOverwrittenSpringConfig {

    // injects EmbeddedServletContainerFactory Bean in Spring IoC Context and overwrites the default one
    @Bean
    public EmbeddedServletContainerFactory factory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setContextPath("/appTomcat");
        return factory;
    }
}

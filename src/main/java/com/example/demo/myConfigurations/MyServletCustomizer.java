package com.example.demo.myConfigurations;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;


/**
 * Setting configuration trough Java code instead of application.properties
 */
@Component
public class MyServletCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
//        configurableEmbeddedServletContainer.setContextPath("/app2");
    }
}

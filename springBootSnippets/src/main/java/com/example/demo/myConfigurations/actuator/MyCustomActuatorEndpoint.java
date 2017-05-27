package com.example.demo.myConfigurations.actuator;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class MyCustomActuatorEndpoint implements Endpoint<String> {
    @Override
    public String getId() {
        return "myCustomActuatorEndpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public String invoke() {
        return "MyCustomActuatorEndpoint  Status as JSON ....";
    }
}

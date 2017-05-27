package com.example.demo.myConfigurations.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
//        return Health.down().withDetail("Error message", "Broken connection").build();
        return Health.status("SOME STATUS").withDetail("Error message", "Broken connection").build();
    }
}

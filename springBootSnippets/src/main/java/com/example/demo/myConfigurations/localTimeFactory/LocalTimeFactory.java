package com.example.demo.myConfigurations.localTimeFactory;

import java.time.LocalDateTime;

// local time Class that can be Mocked while running Tests
public class LocalTimeFactory {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}

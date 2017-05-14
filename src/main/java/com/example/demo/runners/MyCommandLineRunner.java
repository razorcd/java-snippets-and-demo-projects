package com.example.demo.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Will show all the command line properties that the application starts with.
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("These are the CommandLineRunner args:");
        for (String arg : args) {
            System.out.println(arg);
        }
    }

}

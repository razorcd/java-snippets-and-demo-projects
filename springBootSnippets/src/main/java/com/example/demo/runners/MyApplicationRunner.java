package com.example.demo.runners;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Will show all the command line properties that the application starts with.
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("These are the ApplicationRunner args:");
        for (String name : args.getOptionNames()) {
            System.out.println(name + ":" + args.getOptionValues(name));
        }
    }
}

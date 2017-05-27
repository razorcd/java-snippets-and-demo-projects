package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyLoggerController {

    public static final Logger logger = LoggerFactory.getLogger(MyLoggerController.class);

    @GetMapping("/myLogger")
    public String myLogger() {
        logger.info("! Info data from /myLogger !!!!");
        return "see loggs";
    }
}

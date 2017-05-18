package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleSecuredController {

    @RequestMapping("/simplesecured")
    public String simple() {
        return "Simple Secured Endpoint";
    }

}

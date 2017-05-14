package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping({"/", "/my-thymeleaf-template-page"})
    public String myThymeleafTemplatePage() {
        return "/my-thymeleaf-template-page";
    }
}
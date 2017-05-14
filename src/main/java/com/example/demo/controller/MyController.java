package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller   // - when needs templating  OR  use @RestController for rest stuff
public class MyController {

    // to load the my-template-page,html it needs dependency: spring-boot-starter-thymeleaf
    @RequestMapping({"/", "/my-thymeleaf-template-page"})
    public String myThymeleafTemplatePage() {
        return "/my-thymeleaf-template-page";
    }

    @RequestMapping("/my-mustache-page")
    public String myMustachePage(Model model) {
        model.addAttribute("arg1", "arg1 value");
        return "/my-mustache-page";
    }

}

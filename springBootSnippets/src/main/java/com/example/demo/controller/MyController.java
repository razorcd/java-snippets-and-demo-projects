package com.example.demo.controller;

import com.example.demo.myConfigurations.localTimeFactory.LocalTimeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.HashMap;

@Controller   // - when needs templating  OR  use @RestController for rest stuff
public class MyController {

    @Autowired
    CounterService counterService;

    @Autowired
    LocalTimeFactory localTimeFactory;

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

    @RequestMapping(value = "/responseEntity", method = RequestMethod.POST)
    public ResponseEntity<?> getResponseEntity(@RequestBody HashMap<String, String> body, UriComponentsBuilder ucBuilder) {
        System.out.println("! Received body: " + body.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(4).toUri());
        ResponseEntity<String> responseEntity = new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping("/date")
    @ResponseBody // - optional. Creates a Body and serializes to response type. Body is the response, will not look for a tempalte.
    public Date getDate() {

        counterService.increment("customCounter");

        return new Date(109);
    }

    @RequestMapping("/localTime")
    @ResponseBody
    public String localTime() throws InterruptedException {
        String time1 = localTimeFactory.now().toString();
        Thread.sleep(1000);
        String time2 = localTimeFactory.now().toString();

        return time1 + "<br/>" + time2;
    }
}

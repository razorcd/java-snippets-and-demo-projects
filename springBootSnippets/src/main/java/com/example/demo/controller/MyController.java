package com.example.demo.controller;

import com.example.demo.myConfigurations.localTimeFactory.LocalTimeFactory;
import com.example.demo.persistanceSql.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller   // - when needs templating  OR  use @RestController for rest stuff
@RequestMapping   // - not needed
public class MyController {

    @Autowired
    private CounterService counterService;

    @Autowired
    private LocalTimeFactory localTimeFactory;

    // to load the my-template-page,html it needs dependency: spring-boot-starter-thymeleaf
    @RequestMapping({"/", "/my-thymeleaf-template-page"})
    public String myThymeleafTemplatePage() {
        return "/my-thymeleaf-template-page";
    }

//    @RequestMapping("/my-mustache-page")
    @GetMapping("/my-mustache-page")
    public String myMustachePage(Model model) {
        model.addAttribute("arg1", "arg1 value");
        return "/my-mustache-page";
//        return "redirect:/some_other_page";   // Will return 3xx that will force browser to do a new request to new url.
    }

    // Query parameter
    @RequestMapping("/requestParam")
    @ResponseBody
    public String getRequestParam(@RequestParam String myqueryParam,
                                  @RequestParam(defaultValue = "default123") String myDefaultQueryParam) {
        return "the sent query param was: " + myqueryParam + "<br/>" +
                "the default query param was: " + myDefaultQueryParam;
    }

    // Path parameter
    @RequestMapping(value = "/pathParam/{myPathParam}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPathParam(@PathVariable String myPathParam) {
        return "{'the_sent_path_param': '" + myPathParam + "'}";
    }

    // Body parameter
    @RequestMapping(value = "/responseEntity", method = RequestMethod.POST)
    public ResponseEntity<?> getResponseEntity(@RequestBody HashMap<String, String> body, UriComponentsBuilder ucBuilder) {
        System.out.println("! Received body: " + body.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ucBuilder.path("/api/{id}").buildAndExpand(4).toUri());
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Received Body: "+body.toString(), httpHeaders, HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping("/date")
    @ResponseBody // - optional. Creates a Body and serializes to response type. Body is the response, will not look for a tempalte.
    public Date getDate() {

        counterService.increment("customCounter");

        return new Date(109);
    }

    @RequestMapping("/localTime")
    public @ResponseBody String localTime() throws InterruptedException {
        String time1 = localTimeFactory.now().toString();
        Thread.sleep(1000);
        String time2 = localTimeFactory.now().toString();

        return time1 + "<br/>" + time2;
    }

    @RequestMapping("/throwError")
    public void throwError() {
        throw new MissingResourceException("errorMessageHere", StockEntity.class.getName(), "key1");
    }

    // will catch any exception in this controller
    @ExceptionHandler(MissingResourceException.class)
    public @ResponseBody String missingResourceExceptionHandler(Exception e) {
        return "MissingResourceException was thrown. " + e.getMessage();
    }

}

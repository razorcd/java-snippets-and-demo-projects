package com.example.demo.controller;

import com.example.demo.myConfigurations.MyApplicationProperties;
import com.example.demo.myConfigurations.MyConfigurationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
public class MyRestController {
    @Autowired
    @Qualifier("rnd")
    String randomPropValue;

    @Autowired
    @Qualifier("profileMessage")
    String profileMessage;

    @Value("${prop}")
    String prop;

    @Value("${environmentProfile}")
    String environmentProfile;

    @Autowired
    MyConfigurationProperty myConfigurationProperty;

    @Autowired
    ApplicationArguments applicationArguments;  //start app command line arguments

    @Autowired
    MyApplicationProperties myApplicationProperties;  //  my application properties defined with a class and populates by application.yml



    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    @GetMapping(value = "/data", produces = "application/json")
    @ResponseBody
    public HashMap<String, String> data() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("randomPropValue", randomPropValue);
        hm.put("profileMessage", profileMessage);
        hm.put("prop", prop);
        hm.put("property", myConfigurationProperty.getProperty());
        hm.put("applicationArguments", Arrays.stream(applicationArguments.getSourceArgs()).collect(Collectors.toList()).toString());
        return hm;
    }

    @RequestMapping(value = "/environmentProfile", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getEnvironmentProfile() {
        return "Profile dependent prop: " + environmentProfile;
    }

    @RequestMapping(value = "/myapplicationproperties")
    public String myApplicationProperties() {
        return myApplicationProperties.toString();
    }
}

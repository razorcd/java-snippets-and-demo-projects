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

    @Value("${missingprop:defaultMissingPropValue}")   // default value for the prop if it is not defined
//    @Value("${missingprop:${otherPtop:andDeeperDefaultVal}}")
//    @Value("#{myApplicationProperties.testProperties}") // data from other bean
//    @Value("#{myApplicationProperties.data.charAt(5)}") // data from other bean#methods
//    @Value("#{environment['java.home']}") // data from environment variables
//    @Value("#{(1+1>4) ? 'yes' : 'no'}")  // with expressions
//    @Value("#{'abcd' matches '[a-zA-Z0-9\\s]+'}")  // regex
//    @Value("#{{1,2,3,4}}")   // an array if type is an array also
//    @Value("#{{1,2,3,4}.?[#this > 2]}")   // a filtered array (only elements greater than 2
//    @Value("#{{1,2,3,4}.^[#this > 2]}")   // only First element of a filtered array (only elements greater than 2
//    @Value("#{{1,2,3,4}.$[#this > 2]}")   // only Last element of a filtered array (only elements greater than 2
//    @Value("#{{1,2,3,4}.![#this > 2]}")   // projected filtered array. Result e.g.: {false, false, true, true}
//    @Value("#{{'key':'value', 'key2':'value2'}}")   // a map if type is a map also
    String missingProp;

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
        hm.put("missingProp", missingProp);
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

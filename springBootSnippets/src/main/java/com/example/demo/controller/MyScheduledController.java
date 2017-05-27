//package com.example.demo.controller;
//
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.Random;
//
//
//@RestController
//public class MyScheduledController {
//
//    @RequestMapping("/fixedDelayScheduledData")
//    public String fixedDelayScheduledData() {
//        fixedDelayScheduledDataInvocation();
//        return "see server console for delayed method invocation. Wait 10 sec for the time print.";
//    }
//
//    @RequestMapping("/fixedRateScheduledData")
//    public String fixedRateScheduledData() {
//        fixedRateScheduledDataInvocation();
//        return "see server console for repeated prints. Rate: 2sec";
//    }
//
//    @Scheduled(fixedDelay = 10000)
//    private void fixedDelayScheduledDataInvocation() {
//        System.out.println("Delayed: " + String.valueOf(new Random().nextInt()));
//    }
//
//    @Scheduled(fixedRate = 2000)
//    private void fixedRateScheduledDataInvocation() {
//        System.out.println("Repeated: " + new Date().toString());
//    }
//}

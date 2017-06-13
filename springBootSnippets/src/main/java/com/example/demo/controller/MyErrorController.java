package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController {

    //error handled by SpringBoot. The overwritten HttpStatus of exception will be returned.
    @RequestMapping("/myCustomControllerException")
    public String myCustomControllerException() {
        throw new MyCustomControllerException();
    }

    // error handled in the ControllerAdvice
    @RequestMapping("/myCustomControllerExceptionWithErrorAdvice")
    public String myCustomControllerExceptionWithErrorAdvice() {
        throw new MyCustomControllerExceptionWithErrorAdvice();
    }


    // Custom Exception class
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)  // overwrites default status on current request
    public static class MyCustomControllerException extends RuntimeException {}

    public static class MyCustomControllerExceptionWithErrorAdvice extends RuntimeException {}

}

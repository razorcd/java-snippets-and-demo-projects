package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

//@ControllerAdvice  //for sending a view template
@RestControllerAdvice //for data
public class ExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String elementNotFound(NoSuchElementException e) {
        return "Handled error in RestControllerAdvice. Element not found. " + e.getMessage();
    }

    @ExceptionHandler(MyErrorController.MyCustomControllerExceptionWithErrorAdvice.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String myCustomControllerExceptionWithErrorAdviceImpl(MyErrorController.MyCustomControllerExceptionWithErrorAdvice e) {
        return "Handled error in a RestControllerAdvice. My custom controller exception with error advice implementation. " + e.getMessage();
    }
}

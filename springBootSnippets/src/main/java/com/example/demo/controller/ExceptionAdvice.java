package com.example.demo.controller;

import com.example.demo.controller.MyCustomHateoasController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String elementNotFound(NoSuchElementException e) {
        return "Element not found. " + e.getMessage();
    }

    @ExceptionHandler(MyCustomHateoasController.MyCustomControllerExceptionWithErrorAdvice.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public String myCustomControllerExceptionWithErrorAdviceImpl(MyCustomHateoasController.MyCustomControllerExceptionWithErrorAdvice e) {
        return "my custom controller exception with error advice implementation" + e.getMessage();
    }
}

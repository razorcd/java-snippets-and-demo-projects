package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MyCustomHateoasController {

    @Autowired
    MyCustomHateoasResourceAssembler myCustomHateoasResourceAssembler;

    @RequestMapping("/myCustomHateoasResourceWithAssembler")
    @ResponseStatus(HttpStatus.GONE)   // overwrites the default OK response status
    public Resource<String> myCustomHateoasResourceWithAssembler() {
        return myCustomHateoasResourceAssembler.toResource("myCustomHateoas data");
    }


    // Resource Assembler class to decouple creation of HATEOAS links
    // Resource type: String
    @Component
    public static class MyCustomHateoasResourceAssembler implements ResourceAssembler<String, Resource<String>> {
        @Override
        public Resource<String> toResource(String s) {
            return new Resource<>(s,
                    linkTo(methodOn(MyCustomHateoasController.class).myCustomHateoasResourceWithAssembler()).withSelfRel());
        }
    }



//TODO: dig in exception handling
//    @RequestMapping("/myCustomControllerException")
//    public String getDataException() {
//        throw new MyCustomControllerException();
//    }
//
//
//    @RequestMapping("/myCustomControllerExceptionWithErrorAdvice")
//    public String getDataException() {
//        throw new MyCustomControllerExceptionWithErrorAdvice();
//    }


    // Custom Exception class
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // overwrites default status on current request
    public static class MyCustomControllerException extends Exception {

    }

    public static class MyCustomControllerExceptionWithErrorAdvice extends Exception {}
}

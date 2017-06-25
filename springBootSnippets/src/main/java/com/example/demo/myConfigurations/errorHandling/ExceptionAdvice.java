package com.example.demo.myConfigurations.errorHandling;

import com.example.demo.controller.MyErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

//@ControllerAdvice  //for sending a view template
@RestControllerAdvice //for data
public class ExceptionAdvice {

    // a java Exception will be caught
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String elementNotFound(NoSuchElementException e) {
        return "Handled error in RestControllerAdvice. Element not found. " + e.getMessage();
    }

    // a custom Exception will be caught
    @ExceptionHandler(MyErrorController.MyCustomControllerExceptionWithErrorAdvice.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public String myCustomControllerExceptionWithErrorAdviceImpl(MyErrorController.MyCustomControllerExceptionWithErrorAdvice e) {
        return "Handled error in a RestControllerAdvice. My custom controller exception with error advice implementation. " + e.getMessage();
    }

    // DTO Validations: validation exceptions will be caught and the servlet will respond with a list of formatted MyValidationErrorMessageDto objects
    // !! Keeps same content-type as the RequestMapper that rises the Exception. To overwrite content-type here use return ResponseEntity
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody List<MyValidationErrorMessageDto> validationControllerException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().stream()
                .map(MyValidationErrorMessageDto::new)
                .collect(Collectors.toList());
    }
}

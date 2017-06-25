package com.example.demo.myConfigurations.errorHandling;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Optional;

public class MyValidationErrorMessageDto {

    private String message, code, field, rejectedValue, objectName;

    public MyValidationErrorMessageDto(ObjectError objectError) {
        message = objectError.getDefaultMessage();
        code = objectError.getCode();
        if(objectError instanceof FieldError) {
            field = ((FieldError) objectError).getField();
            Optional.ofNullable(((FieldError) objectError).getRejectedValue()).ifPresent(fieldErr -> rejectedValue = fieldErr.toString());
        }
        objectName = objectError.getObjectName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}

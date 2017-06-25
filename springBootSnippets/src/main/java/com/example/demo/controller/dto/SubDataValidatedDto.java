package com.example.demo.controller.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SubDataValidatedDto {

    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "must contain only numbers and letters")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String encriptedPassword;

    public SubDataValidatedDto() {
    }

    public SubDataValidatedDto(String username, String encriptedPassword) {
        this.username = username;
        this.encriptedPassword = encriptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncriptedPassword() {
        return encriptedPassword;
    }

    public void setEncriptedPassword(String encriptedPassword) {
        this.encriptedPassword = encriptedPassword;
    }
}

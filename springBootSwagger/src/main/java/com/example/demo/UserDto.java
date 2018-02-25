package com.example.demo;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {

    @ApiModelProperty(notes = "User's name")
    private String name;

    @ApiModelProperty(notes = "User's age")
    private int age;

    @ApiModelProperty(notes = "User's availability")
    private boolean available;

    public UserDto() {
    }

    public UserDto(String name, int age, boolean available) {
        this.name = name;
        this.age = age;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

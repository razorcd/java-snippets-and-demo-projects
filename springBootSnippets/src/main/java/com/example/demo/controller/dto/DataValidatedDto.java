package com.example.demo.controller.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import sun.invoke.empty.Empty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

public class DataValidatedDto {

//    @NotBlank(groups = Empty.class)  // to validate based on a selected group
    @Length(min = 4, max = 10)
    private String name;

    @Email
    private String email;

    @Min(21)
    private int age;

    @NotNull
    @Digits(integer = 3, fraction = 2, message = "must have maximum 3 integer digits and maximum 2 fraction digits.")
    private double amount;

    @AssertTrue // null is accepted
    private boolean free;

    @Past
    private Date birthday;

    @Valid
    private SubDataValidatedDto subData;

    public DataValidatedDto() {
    }

    public DataValidatedDto(String name, String email, int age, double amount, boolean free, Date birthday, SubDataValidatedDto subData) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.amount = amount;
        this.free = free;
        this.birthday = birthday;
        this.subData = subData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public SubDataValidatedDto getSubData() {
        return subData;
    }

    public void setSubData(SubDataValidatedDto subData) {
        this.subData = subData;
    }
}

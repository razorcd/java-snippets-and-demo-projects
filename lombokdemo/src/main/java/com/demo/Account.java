package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data // creates getters, setters, toString, hashCode and equals
@AllArgsConstructor
public class Account {

    private String name;
    private BigDecimal amount;
    private boolean blocked;

}

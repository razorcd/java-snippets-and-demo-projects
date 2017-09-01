package com.demo;

import java.math.BigDecimal;

public class Bank {

    private BigDecimal currentAmount;
    private BigDecimal lastAmount;
    private String name;
    private int profit; // %


    public Bank(BigDecimal amount, String name) {
        if (amount.intValue()<=0) throw new IllegalArgumentException("Amount value must be positive.");
        this.currentAmount = amount;
        this.name = name;
        this.profit = 100;
    }

    public void addAmmount(BigDecimal value) {
        lastAmount = currentAmount;
        currentAmount = value.add(lastAmount);
        calculateProfit();
    }

    public void tryToCalculate() {
        throw new ArithmeticException("Can not calculate.");
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfit() {
        return profit;
    }

    private void calculateProfit() {
        profit = currentAmount.multiply(new BigDecimal(100)).divide(lastAmount).intValue();
    }
}

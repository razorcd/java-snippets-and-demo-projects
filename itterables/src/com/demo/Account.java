package com.demo;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * @since 1.7
 */
public class Account implements Comparable<Account>{

    private String name;
    private BigDecimal balance;


    public Account(String name, BigDecimal balance) {
        Objects.requireNonNull(balance, "(0x215) Balance can not be null.");   // just for fun
        this.name = name;
        this.balance = balance;
    }


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Account o) {
        return this.balance.compareTo(o.balance);
    }

    /**
     * @since 1.7
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getName(), account.getName()) &&
                Objects.equals(getBalance(), account.getBalance());
    }

    /**
     * @since 1.7
     */
    @Override
    public int hashCode() {

        return Objects.hash(getName(), getBalance());
    }
}

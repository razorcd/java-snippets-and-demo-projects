package com.demo.oldIO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

// must implement Serializable to be writable to a stream (File)
public class MyEntity implements Serializable {
    private String name;
    private Date date;
    private BigDecimal amount;
    private boolean active;


    static final long serialVersionUID = 43L;    // used to define the version of the class implementation. Any change to the class should increase this value.


    public MyEntity(String name, Date date, BigDecimal amount, boolean active) {
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyEntity myEntity = (MyEntity) o;
        return active == myEntity.active &&
                Objects.equals(name, myEntity.name) &&
                Objects.equals(date, myEntity.date) &&
                Objects.equals(amount, myEntity.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, amount, active);
    }
}

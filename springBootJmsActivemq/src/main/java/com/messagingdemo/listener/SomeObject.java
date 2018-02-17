package com.messagingdemo.listener;

import java.io.Serializable;
import java.util.Objects;

public class SomeObject implements Serializable {

    private String _id;
    private String name;
    private int age;
    private boolean active;

    public SomeObject() {
    }

    public SomeObject(String _id, String name, int age, boolean active) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeObject that = (SomeObject) o;
        return age == that.age &&
                active == that.active &&
                Objects.equals(_id, that._id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, name, age, active);
    }
}

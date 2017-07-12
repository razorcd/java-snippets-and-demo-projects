package main.java.classes.abstractClasses;


import java.io.Serializable;

/**
 * Difference between Interfaces and Abstract Classes. Abstract classes can:
 * - define properties
 * - define method implementations that don't need to be overridden
 * - define constructors
 * - define public/static on abstract methods
 *
 * ! myClass is a {Abstract class here}.  myClass can {Interface here}.    (Dog is an Animal. Dog can implement Movable)
 * ! Abstract classes can implement interfaces
 */
public abstract class Animal implements Serializable {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

//    public abstract Animal();   // abstract not allowed on constructor methods

    public abstract void say();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

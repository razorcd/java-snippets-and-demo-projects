package main.java.classes.abstract_classes;


import java.io.Serializable;

/**
 * Difference between Interfaces and Abstract Classes. Abstract classes can:
 * - define properties
 * - define method implementations that don't need to be overridden
 * - define constructors
 * - define public/static on abstract methods
 *
 * ! myClass is a {Abstract class here}.  myClass can {Interface here}.    (Dog is an Animal. Dog can implement Movable)
 * ! Abstract classes can also implement interfaces.
 * ! Abstract classes are not instantiable
 */
public abstract class Animal implements Serializable {
    private String name;   // Subclasses will NOT have access to this Private field
    protected String name2;  // Subclasses will have access to this Protected field

    protected Animal(String name) {  // protected constructor makes more sense in an abstract class
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

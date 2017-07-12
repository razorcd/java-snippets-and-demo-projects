package main.java.classes.abstractClasses;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);  // calls abstract class constructor
    }

    @Override
    public void say() {
        System.out.println("Woof!");
    }

    // new method
    public void sayMore() {
        System.out.println("Woof woof woof!");
    }
}

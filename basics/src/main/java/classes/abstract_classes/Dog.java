package main.java.classes.abstract_classes;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);  // calls abstract class constructor
//        this.name = "";  // private method from main abstract class not accessible here
        this.name2 = "a name"; // protected method is accessible here
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

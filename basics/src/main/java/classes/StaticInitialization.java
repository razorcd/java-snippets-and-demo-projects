package main.java.classes;

public class StaticInitialization {

    public static String staticVar = "Static var";

    public static void  someStaticMethod() {
        System.out.println("StaticInitialization - some static method call");
    }

    // Static Initializer is called at First Access to the class (Static var or method or constructor)
    // ! it is called only one time
    static {
        System.out.println("StaticInitialization - static 1");
    }


    public StaticInitialization() {
        System.out.println("StaticInitialization - constructor");
    }


    // all Static Initializer blocks are called in order
    static {
        System.out.println("StaticInitialization - static 2");
    }


    public void someMethod() {
        System.out.println("StaticInitialization - some instance method call");
    }
}

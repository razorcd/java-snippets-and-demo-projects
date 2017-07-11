package main.java;

public class Constant {

    public static void run() {

//        const  // - reserved word but has no functionality

        final int FOO = 5;  // imutable (constant)

//        FOO = 6;   // Compile error
        System.out.println(FOO);
    }
}

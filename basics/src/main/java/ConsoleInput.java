package main.java;

import java.util.Scanner;

public class ConsoleInput {

    Scanner scanner = new Scanner(System.in);

    public void run() {

        System.out.println("Enter a number.");

        int i = scanner.nextInt();     // starts interactive shell and expects user to enter an int

        System.out.println("You entered: "+i);

    }
}

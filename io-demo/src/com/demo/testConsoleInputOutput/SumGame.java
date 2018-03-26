package com.demo.testConsoleInputOutput;

import java.util.Scanner;

public class SumGame {

    public static void main(String[] args) {

        System.out.println("Started Sum Calculator.");

        try (Scanner in = new Scanner(System.in)) {

            int value1 = Integer.parseInt(in.nextLine().trim());
            System.out.println("plus");
            int value2 = Integer.parseInt(in.nextLine().trim());

            System.out.println("Equals");
            System.out.println(value1 + value2);

        }
    }
}

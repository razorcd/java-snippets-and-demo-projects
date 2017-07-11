package main.java;

import main.java.classes.ValueObject;

import java.util.ArrayList;

public class ArraysDemo {

    public static void run() {

        //arrays.  Fixed size.
        int[] myInt0 = new int[] {};  //size 0
        int[] myInt1 = new int[4];   //array of 4 elements
        int[] myInt2 = new int[] {3,6,8,4};
        int[] myInt3 = {5,2,7,2};

        System.out.println("Array length: "+ myInt0.length);
        System.out.println(myInt2[1]);


        //ArrayList
        ArrayList<String> words = new ArrayList<>();
        words.add("one"); words.add("two"); words.add("three"); words.add(null);

        words.set(2, "three again");   // replacing "three"
        words.remove(1); // removes "two"

        System.out.println("words size is: " + words.size());
        System.out.println("elem 0 of words is: " + words.get(0));
        System.out.println("index of 'three again' is: " + words.indexOf("three again"));
        System.out.println(words);


        //Copy Array Lists
        ArrayList<String> newWords = new ArrayList<>();
        newWords.addAll(words);
        words.set(1, "!"); // does not affect `newWords` list

        System.out.println(words);
        System.out.println(newWords);

        //Copy Array Lists of ValueObject
        ArrayList<ValueObject> values = new ArrayList<>();
        values.add(new ValueObject(5));
        values.add(new ValueObject("five"));
        values.add(new ValueObject(5.0f));

        ArrayList<ValueObject> values2 = new ArrayList<>(values);   // same as using `values2.addAll(values)`
        values.set(1, new ValueObject("!")); // does not affect `values2`
        values.get(2).setValue("!!!");       // ! affects both `values` and `values2`. Same reference.

        System.out.println(values);
        System.out.println(values2);


        // Copy ArrayList to regular Array
        ValueObject[] regularValues = new ValueObject[values.size()];
        values.toArray(regularValues);   // regularValues will have the new values
        System.out.println("Copy to regular array, elem 2: " + regularValues[2]);

    }
}

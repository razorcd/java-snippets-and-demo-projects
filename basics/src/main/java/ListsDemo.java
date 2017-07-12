package main.java;

import main.java.classes.ValueObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListsDemo {

    public static void run() {

        //arrays.  Fixed size.
        int[] myInt0 = new int[] {};  //size 0
        int[] myInt1 = new int[4];   //array of 4 elements
        int[] myInt2 = new int[] {3,6,8,4};
        int[] myInt3 = {5,2,7,2};

        System.out.println("Array length: "+ myInt0.length);
        System.out.println(myInt2[1]);


        //ArrayList    (inside, for each element, it stores: Index, Address, Value||Reference)
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


        //autoboxing and unboxing
        Integer a = 55; //compiler does: Integer.valueOf(55)   or   new Integer(55)
        int b = a; // compiler does: a.intValue()
        ArrayList<Integer> aaa = new ArrayList<>(); // because `ArrayList<Integer>` does not work
        aaa.add(55);




        // Linked Lists (inside, for each element, it stores: Index, Address, Value||Reference)
        LinkedList<Double> ll = new LinkedList<>() ;
        ll.add(1.5d); ll.add(13.5d); ll.add(4.3d);
        System.out.println("Linked list - first elem: " + ll.getFirst());
        System.out.println("Linked list - last elem: " + ll.getLast());
        System.out.println("Linked list - peek (current position): " + ll.peek());
        System.out.println("Linked list - pop (removes first): " + ll.pop());
        System.out.println("Linked list: " + ll);

        // Iterate over Linked Lists
        Iterator<Double> iterator = ll.iterator();
        while(iterator.hasNext()) { System.out.print(iterator.next() + " "); }
        System.out.println();
        for (Double d : ll) { System.out.print(d + " "); }
        System.out.println();

    }
}

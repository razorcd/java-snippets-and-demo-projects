package com.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name1", 44, true),
                new PersonEntity("Name2", 33, false),
                new PersonEntity("Name3", 40, true)
        );


        // Use a Lambda
        Collections.sort(personEntities, (p1, p2) -> p1.getAge()-p2.getAge());
        System.out.println(personEntities);


        // Define a Lambda to reuse
        final int var = 0;  // variables used in lambdas from outer scope must be final
        Humanizable hum = (s1, s2) -> var + ". " + s1.toLowerCase() + " " + s2.toLowerCase();   // requires an Interface with one method definision
        String result = hum.humanize("alpha", "beta");
//        var++;
        System.out.println("humanize: " + result);




        // Use Lambda as a Runnable and new Threads
        Runnable r = () -> {
            System.out.println("Started runnable labda.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted " + e.getMessage() );
            }
            System.out.println("Finishing runnable lambda.");
        };

        r.run();
        new Thread(r).start();
        new Thread(() -> System.out.println("Called from another thread.")).start();

        // My Implementation of ForEach
        MyForEachImplementation.run();




        //Predicates


    }
}

// interface for a Lambda
interface Humanizable {
    public String humanize(String s1, String s2);
}
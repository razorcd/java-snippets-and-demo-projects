package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name5", 44, true),
                new PersonEntity("Name1", 33, false),
                new PersonEntity("Name3", 40, true)
        );

        List<String> names = personEntities
                .stream()
                .map((p) -> p.getName())
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);   // collect in ArrayList
//                .collect(Collectors.toList());


        System.out.println(names);

        personEntities
                .stream()
                .map((p) -> p.getName())
                .sorted()
                .forEach(System.out::println);  // Class::Method
//                .forEach((String s) -> System.out.println(s));

        Stream<Integer> stream1 = Stream.of(4,6,3,7,2, 10);
        Stream<Integer> stream2 = Stream.of(14,16,13,17,12, 10);

        //concatenate streams
        Stream<Integer> strem12 = Stream.concat(stream1, stream2);

        long count = strem12
                .distinct()
                .peek(System.out::println)  // executes method and returns same stream
                .count();

        System.out.println("Distinct elements count: " + count);


        //lazy evaluation
        Stream.of(1,2,3,4,6)
                .filter(s -> {
                    System.out.println(s);
                    return true;
                });             // without a terminal operation this Method call will not be executed!
//                .count();


    }
}


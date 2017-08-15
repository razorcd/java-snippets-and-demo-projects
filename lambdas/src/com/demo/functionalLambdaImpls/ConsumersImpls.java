package com.demo.functionalLambdaImpls;

import com.demo.PersonEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

// Consumer functions (One OBJECT ARGUMENT, NO RETURN)
public class ConsumersImpls {
    public static void run() {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name11", 44, true),
                new PersonEntity("Name22", 33, false),
                new PersonEntity("Name33", 40, true)
        );

        // use consumer as lambda:
        Consumer<PersonEntity> consumerLambda = (p) -> System.out.println(p.getAge());
        printPerson(personEntities, consumerLambda);
//        printPerson(personEntities, (p) -> System.out.println(p.getAge()));
    }

    // Consumer will eval the lambda.  Use it to define executions on an object.
    private static void printPerson(List<PersonEntity> personEntities, Consumer<PersonEntity> consumer) {
        for (PersonEntity p : personEntities) {
            System.out.println("Consumer: Starting execution.");
            consumer.accept(p);
            consumer.andThen(consumer);
        }
    }
}

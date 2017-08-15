package com.demo.functionalLambdaImpls;

import com.demo.PersonEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// Predicate functions (One OBJECT ARGUMENT, BOOLEAN RETURN)
public class PredicatesImpls {
    public static void run() {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name1", 44, true),
                new PersonEntity("Name2", 33, false),
                new PersonEntity("Name3", 40, true)
        );

        // use predicate as lambda:
        System.out.println("Predicate. Younger than 40:");
        printPersonIfCondition(personEntities, (p) -> p.getAge() < 40);

        System.out.println("Predicate. Older than 40:");
        Predicate<PersonEntity> predicateLambda = (p) -> p.getAge() > 40;
        printPersonIfCondition(personEntities, predicateLambda);
//        printPersonIfCondition(personEntities, (p) -> p.getAge() > 40);

        // use predicate as anonymous class:
        System.out.println("Predicate. Equal to 40:");
        printPersonIfCondition(personEntities, new Predicate<PersonEntity>() {
            @Override
            public boolean test(PersonEntity personEntity) {
                return personEntity.getAge() == 40;
            }
        });

    }


    // Predicate will eval the lambda end rerun boolean.  Use it to define conditional lambdas.
    private static void printPersonIfCondition(List<PersonEntity> personEntities, Predicate<PersonEntity> condition) {
        for (PersonEntity p : personEntities) {
            if (condition.test(p))
                System.out.println("Predicate: Person " + p.getName() + " passes condition.");
        }
    }
}

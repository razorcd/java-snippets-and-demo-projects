package com.demo.functionalLambdaImpls;

import com.demo.PersonEntity;

import java.util.Arrays;
import java.util.List;

// Runnable functions (NO ARGUMENTS, NO RETURN)
public class RunnablesImpls {
    public static void run() {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name111", 44, true),
                new PersonEntity("Name221", 33, false),
                new PersonEntity("Name331", 40, true)
        );

        // use runnable as lambda:
        Runnable runnableLambda = () -> System.out.println("Running runnable");
        printPerson(personEntities, runnableLambda);
//        printPerson(personEntities, () -> System.out.println("Running runnable"));
    }

    // Runnable will eval the lambda.  Use it to define executions with NO ARGUMENTS and NO RETURN.
    private static void printPerson(List<PersonEntity> personEntities, Runnable runnable) {
        for (PersonEntity p : personEntities) {
            System.out.println("Runnable: Starting execution for" + p.getName());
            runnable.run();
        }
    }
}

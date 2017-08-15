package com.demo.functionalLambdaImpls;

import com.demo.PersonEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

// Function functions (One OBJECT ARGUMENTS, OBJECT RETURN)
// ! if ARGUMENT and RETURN are the same type consider using UnaryOperator
public class FunctionsImpls {
    public static void run() {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name111", 44, true),
                new PersonEntity("Name222", 33, false),
                new PersonEntity("Name333", 40, true)
        );

        // use function as lambda:
        Function<PersonEntity, String> funcionLambda = (p) -> {return p.getName() + " !!!!";};
        printPerson(personEntities, funcionLambda);
//        printPerson(personEntities, (p) -> {return p.getName() + " !!!!";});
    }

    // Function will eval the lambda and return an object.  Use it to define executions with OBJECT ARGUMENT and OBJECT RETURN.
    private static void printPerson(List<PersonEntity> personEntities, Function<PersonEntity, String> function) {
        for (PersonEntity p : personEntities) {
            System.out.println("Function: Return execution " + function.apply(p));
        }
    }
}

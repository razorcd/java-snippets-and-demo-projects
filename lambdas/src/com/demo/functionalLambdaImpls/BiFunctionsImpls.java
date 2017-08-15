package com.demo.functionalLambdaImpls;

import com.demo.PersonEntity;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

// BiFunction functions (Two OBJECT ARGUMENTS, OBJECT RETURN)
public class BiFunctionsImpls {
    public static void run() {

        List<PersonEntity> personEntities = Arrays.asList(
                new PersonEntity("Name111", 44, true),
                new PersonEntity("Name222", 33, false),
                new PersonEntity("Name333", 40, true)
        );

        // use bifunction as lambda:
        BiFunction<PersonEntity, Integer, String> bifuncionLambda = (p, i) -> {return i+". " + p.getName() + " !!!!";};
        printPerson(personEntities, bifuncionLambda);
//        printPerson(personEntities, (p, i) -> {return i+". " + p.getName() + " !!!!";});
    }

    // Function will eval the lambda and return an object.  Use it to define executions with OBJECT ARGUMENT and OBJECT RETURN.
    private static void printPerson(List<PersonEntity> personEntities, BiFunction<PersonEntity, Integer, String> bifunction) {
        for (int i = 0; i < personEntities.size(); i++) {
            System.out.println("BiFunction: Return execution " + bifunction.apply(personEntities.get(i), i));

        }
    }
}

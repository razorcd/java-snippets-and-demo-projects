package com.demo;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;


public interface MyTestHelper {

    List<Integer> randomNumbersList = getRandomNumberList();
    int[] getValues();


    @BeforeEach
    default void beforeEachFromInterface() {
        System.out.println("Before each from interface.");
    }

    @AfterEach
    default void afterEachFromInterface() {
        System.out.println("After each from interface.\n");
    }

    // default method
    @Test
    @DisplayName("Default data must be set")   // test/method that will run before all other tests.
    default void defaultData() {
        Assertions.assertTrue(true, "Default assertion. Data for testing should be set.");
    }

    // static method
    static List<Integer> getRandomNumberList() {
        return new Random().ints(5, 0, 10).boxed().collect(Collectors.toList());
    }


}

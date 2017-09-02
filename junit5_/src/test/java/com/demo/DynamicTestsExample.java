package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import javax.xml.ws.FaultAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicTestsExample {

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }


    @TestFactory // can be collection, stream or iterator
    Collection<DynamicTest> dynamicTestFromCollection() {
        return Arrays.asList(
                DynamicTest.dynamicTest(
                        "1st dynamic test",
                        () -> assertEquals(1,1)
                ),
                DynamicTest.dynamicTest(
                        "2nd dynamic test",
                        () -> assertEquals(1,1)
                )
        );
    }









}

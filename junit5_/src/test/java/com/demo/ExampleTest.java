package com.demo;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)   // to create different ExampleTest instance for each UnitTest (method)
@DisplayName("Testing Example")
class ExampleTest implements MyTestHelper {   // interface to define general helpers

    private Example subject = null;

    public ExampleTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("BeforeAll\n");
    }

    @BeforeEach
    void setUp() {
        subject = new Example();   // initialize a new Subject for each unit test

        System.out.println("BeforeEach");
        System.out.println("Got values: " + Arrays.toString(getValues()));
    }

    @AfterEach
    void tearDown() {
        System.out.println("AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }




    @Override  // required by the Interface
    public int[] getValues() {
        return randomNumbersList.stream().mapToInt(Integer::intValue).toArray();
    }


//    @Tag("one")
    @Test
    void firstTest() {
        int result = subject.sum(5,3);
        assertEquals(8, result);
    }

//    @Tag("two")
    @Test
    void secondTest() {
        assertAll( "all lambdas should pass",   //  if one assertion fails, the other will still be executed
                () -> assertNotNull(3),
                () -> assertTrue(true)
        );

    }

    @Test
    @DisplayName("Assertions built in JUnit")  // for more use AssertJ or Hamcrest
    void assertionsBuiltInJUnitTest() throws InterruptedException {
        Object o1 = new Object(); Object o2 = o1;

        assertEquals(5_000,5_000,"Failure message here");  // compares using equals
        assertEquals(5,5.1, 0.1,"Failure message here");  // compares using equals
        assertNotEquals(5,6);  // compares using equals
        assertSame(o1, o2);  // compares references
        assertNotSame(new BigDecimal(5), new BigDecimal(5));  // compares references
        assertTrue(true);
        assertFalse(false);
        assertNull(null);
        assertNotNull("not null");
        assertArrayEquals(new int[]{1,2,3,4}, new int[]{1,2,3,4});
        assertIterableEquals(Arrays.asList(1,2,3,4), Arrays.asList(1,2,3,4));
        assertLinesMatch(Arrays.asList("a1", "a2"), Arrays.asList("a1", "a2"));
        assertTimeout(Duration.ofMillis(50), () -> Thread.sleep(10L));  // ensures it finishes in time. Executed in SAME Thread. Does not abort execution if timeout exceeds.
        assertTimeoutPreemptively(Duration.ofMillis(50), () -> Thread.sleep(10L));  // ensures it finishes in time. Executed in a NEW Thread. Aborts execution if timeout exceeds.
        assertThrows(IOException.class, () -> { throw new IOException("Intentional thrown Exception"); });
//        fail("Failed intentionally");   // fail a unit test intentionally.
    }

    @Disabled // works on methods and classes
    @Test
    @DisplayName("Disabled test")
    void disabledTest() {
        fail("this should be disabled");
    }



    @Test
    @DisplayName("Assumptions")
    void assumptionsTest() {
        assumeTrue("i".equalsIgnoreCase("I"), "connection not avaliable");   // stop execution of a test. No test failure, just abort the test.
        assumeFalse(false);

        assumingThat(true, () -> {
            assertTrue(true);
            System.out.println("! do something");
        });  // JUST Executes the lambda if boolean is true. nothing else.

        assumingThat(false, () -> {
            fail("this should not run");
            System.out.println("! do something");
        });

//        fail("this should not run");
        assertTrue(true);
    }




//    @Test  // because it is a repeated test
    @RepeatedTest(value = 3, name = "{displayName} -> {currentRepetition}/{totalRepetitions}")
    @DisplayName("My repeated tests")
    void someRepeatedTests(RepetitionInfo repetitionInfo) {
        System.out.println("! Repeated test: " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
    }


}
package com.demo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BankTest {

    Bank subject;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class.");
    }


    @org.junit.Before
    public void setUp() throws Exception {
        System.out.println("Before each method.");
        subject = new Bank(new BigDecimal(1000), "B1");
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.out.println("After each method.");
    }



    
    @org.junit.Test
    public void addAmmount() {
//        fail("Not yet implemented.");

        assertEquals("B1", subject.getName());
        assertEquals(new BigDecimal(1000), subject.getCurrentAmount());
        assertEquals(100, subject.getProfit());

        subject.addAmmount(new BigDecimal(250));

        assertEquals(new BigDecimal(1250), subject.getCurrentAmount());
        assertEquals(125, subject.getProfit());
    }

    @Test
    public void otherTest() {
        assertEquals(1000, 1000.1, 0.2);  // with error limit.  Uses .equal method to compare objects!
        assertNotEquals(1,2);
        assertArrayEquals(new int[]{1,2,3}, new int[]{1,2,3});
        assertTrue("Should be true.",true);
        assertFalse("Should be false.", false);
        assertNull(null);
        assertNotNull(1);
        assertSame("1", "1");   // uses Object Reference to compare
//        assertThat("A", Matcher);
    }

    @Test(expected = ArithmeticException.class)
    public void tryToCalculateTest1() {
        subject.tryToCalculate();
        fail("Should have thrown an ilegal argument exception.");  // successful test will not reach this line
    }
    // OR
    @Test
    public void tryToCalculateTest2() {
        try {
            subject.tryToCalculate();
            fail("Should have thrown an ilegal argument exception.");  // successful test will not reach this line
        } catch (ArithmeticException e) {
        }
    }
}
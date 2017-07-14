package com.demo;

import java.util.NoSuchElementException;

public class Paradigms {

    /**
     * Look Before You Leave
     */
    public static int divideLBYL(int x, int y) {
        if (y == 0) { return 0; }
        return x / y;
    }

    /**
     * Easy to Ask for Forgiveness and Permission
     */
    public static int divideEAFP(int x, int y) {
        try {
            return x / y;
//        } catch (NoSuchElementException | ArithmeticException ex) {   // catch multiple exceptions
        } catch (NoSuchElementException ex) {   // catch multiple exceptions
            throw new NoSuchElementException("Invalid input.");
        } catch (ArithmeticException ex) {
            throw new ArithmeticException("Attempt to divide by 0.");
//            return 0;
        }
    }

    /**
     * Divide
     */
    public static int divide(int x, int y) {
        return x / y;
    }
}

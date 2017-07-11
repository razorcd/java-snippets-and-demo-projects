package main.java;

public class PrimitiveTypes {

    public static void run() {

        System.out.println("\n---" + PrimitiveTypes.class);


        // ########################
        //     Primitive types
        // ########################

        //int - 32 bit
        int minInt = -2_147_483_648;
        int maxInt = 2_147_483_647;

        //byte - 8 bit
        byte minByte = -128;
        byte maxByte = 127;


        //short - 16 bit
        short minShort = -32768;
        short maxShort = 32767;

        //long - 64 bit
        long minLong = -9_223_372_036_854_775_808L;
        long maxLong = 9_223_372_036_854_775_807L;


        long aLong = maxInt;
        int aInt = (int) aLong;   // cuts the extra bits and reduces to 32bit

        System.out.println("Long casted to int: " + aInt);
        System.out.println("10 / 3 = " + 10f/3);



        //DECIMAL numbers:

        //single precision (7 digits) - 32 bit
        float aFloat = 123456789.1234567890123456789f;

        //double precision (16 digits) - 64 bit
        double aDouble = 123456789.1234567890123456789d;

        System.out.println(aFloat);
        System.out.println(aDouble);

        boolean isDone = true;

        //char
        char aChar = '\u00A9'; // unicode character.   Characters list: https://unicode-table.com/en
        System.out.println("Unicode character: " + aChar);



        //string  (not primitive, it is a sequence of characters)
        String aString = "sakfjaslf\u00AE";
        System.out.println("String: " + aString);
        System.out.println(aString instanceof String);

    }
}

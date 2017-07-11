package main.java;

public class BitwiseOperators {

    public static void run() {
        System.out.println("\n---" + BitwiseOperators.class);

        int a = 12; // 00001100 (In Binary) |
        int b = 25; // 00011001 (In Binary) =
        int r = 29; // 00011101 (In Binary)

        System.out.println("OR:         a|b=" + (a | b) );  // performs OR on each bit
        System.out.println("AND:        a&b=" + (a & b) );  // performs AND on each bit
        System.out.println("XOR         a^b=" + (a ^ b) );  // performs XOR on each bit
        System.out.println("Complement: ~a=" + (~a) );      // performs Complement (invert) on each bit
        System.out.println("L shift:    a<<2=" + (a << 2) );  // performs Lshift on bits 2 times (NOT CIRCULAR)
        System.out.println("R shift:    a>>3=" + (a >> 3) );  // performs Rshift on bits 3 times  (NOT CIRCULAR)
        System.out.println("R unsigned shift:    a>>>1=" + (a >>> 1) );  // performs Rshift 1 time ignoring sign character (NOT CIRCULAR)
    }
}

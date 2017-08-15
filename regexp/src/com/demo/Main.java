package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String s = "abcd123 xabcdx123_11ab";
        System.out.println(s);

        String newString = s.replaceAll("abcd", "ABCD");
        System.out.println(newString);

        String newString2 = s.replaceAll(".", "ABCD");
        System.out.println(newString2);

        String newString3 = s.replaceAll("^abcd", "ABCD");
        System.out.println(newString3);

        String newString4 = s.replaceAll("ab$", "ABCD");
        System.out.println(newString4);

        String newString5 = s.replaceAll("[3a1x]", "_");  // replace a || 1 || x
        System.out.println(newString5);

        String newString6 = s.replaceAll("[^3a1x]", "_");  // replace not(a || 1 || x)
        System.out.println(newString6);

        String newString7 = s.replaceAll("[a1x][ba]", "ABCD");  // replace a || 1 || x oly if followed by b || a
        System.out.println(newString7);

        boolean m = s.matches("^abcd.*ab$");
        System.out.println("String matched '^abcd.*ab$': " + m);   // always matches the whole string !




        // define patters

        Pattern pattern = Pattern.compile("a[0-9]+z", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher =  pattern.matcher("aaa100zzz");
        System.out.println("Regex pattern matcher find: " + matcher.find());   // sets cursor and returns t|f
        System.out.println("Regex pattern matcher position: " + matcher.start());  // prints current position of Cursor



    }
}

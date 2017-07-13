package com.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MySets {

    public static void main(String[] args) {

        Set<String> setA = new HashSet<>(Arrays.asList("a", "b", "c", "d"));
        Set<String> setB = new HashSet<>(Arrays.asList("c", "d", "e", "f"));
        Set<String> setC = new HashSet<>(Arrays.asList("c", "d"));

        printlnSet(setA, "setA:");
        printlnSet(setB, "setB:");
        printlnSet(setC, "setC:");

        System.out.println("setA contains 'a'? : " + setA.contains("a"));
        System.out.println("setA containsAll setC? : " + ((HashSet) setA).containsAll(setC));

        //difference A-B
        Set<String> diff = new HashSet<>(setA); diff.removeAll(setB);  // needs a new `diff` HashSet so we don't modify original `setA`
        printlnSet(diff, "setA - setB. diff: ");

        //intersection  AxB
        Set<String> intersection = new HashSet<>(setA); intersection.retainAll(setB);
        printlnSet(intersection, "setA intersected with setB. intersection:");

        //union
        Set<String> union = new HashSet<>(setA); union.addAll(setB);
        printlnSet(union, "setA + setB. union:");


        //asymmetric difference  (what is in setA   and   what is in setB   but not in Both)
        Set<String> asym = new HashSet<>(union); asym.removeAll(intersection);
        printlnSet(asym, "asymmetric difference between setA and setB");

    }

    public static void printlnSet(Set<String> set, String message) {
        System.out.print(message + "  ");
        for (String s : set) {
            System.out.print(s+" ");
        }
        System.out.println();
    }
}

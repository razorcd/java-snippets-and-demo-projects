package com.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyMaps {
    public static void main(String[] args) {

        Map<String, Account> accountMap = new HashMap<>(); // will not keep order. Only LinkedHasMap keeps order. TreeMap also keeps order and it's self sorting by key.
        accountMap.put("John", new Account("Ac1", new BigDecimal(100)));
        accountMap.put("Jane", new Account("Ac2", new BigDecimal(400)));
        accountMap.put("Jr", new Account("Ac3", new BigDecimal(200)));

        // overwrite key
        Account oldJr = accountMap.put("Jr", new Account("Ac4", new BigDecimal(800)));  // overwrites old value for "Jr" and returns old value
        System.out.println("Old balance for Jr: " + oldJr.getBalance());
        System.out.println("Current balance for Jr: " + accountMap.get("Jr").getBalance());

        // check if key exists
        System.out.println("John exists? : " + accountMap.containsKey("John"));

        // add if absent (good for concurrency)
        Account johnAccount = accountMap.putIfAbsent("John", new Account("Ac6", new BigDecimal(500)));  // returns existing account for "John".  Does not overwrite.
        System.out.println("Old balance for John: " + johnAccount.getBalance());
        System.out.println("Current balance for John: " + accountMap.get("John").getBalance());

        // remove
        accountMap.remove("Jane");

        printlnMap(accountMap, "accountMap:");


        // Encapsulation
        MyEncapsulatedMaps myEncapsulatedMaps = new MyEncapsulatedMaps(accountMap);
        accountMap.put("extraAc", new Account("acc", new BigDecimal(999)));
        accountMap.replace("John", new Account("acc9", new BigDecimal(888)));
        Map<String, Account> newMap = myEncapsulatedMaps.getMyMaps();
        newMap.put("extraAc2", new Account("acc2", new BigDecimal(998)));
        accountMap.replace("John", new Account("acc8", new BigDecimal(887)));
//        newMap.get("John").setBalance(new BigDecimal(777));    // will change the value inside myEncapsulatedMaps -> account-"John"
        printlnMap(myEncapsulatedMaps.getMyMaps(), "myEncapsulatedMaps map:");
    }

    private static void printlnMap(Map<String, Account> accountMap, String message) {
        System.out.println(message);
        for (String key : accountMap.keySet() ) {
            System.out.println(" " + key + " has balance: " +
                    String.format("%.2f", accountMap.get(key).getBalance()));   // format number with 2 decimals
        }
        System.out.println();

    }
}

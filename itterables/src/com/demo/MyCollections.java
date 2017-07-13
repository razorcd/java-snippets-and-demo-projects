package com.demo;

import java.math.BigDecimal;
import java.util.*;

public class MyCollections {

    public static void main(String[] args) {


        //ARRAYS

        // sort (Needs  class Account implements Comparable<Account>)
        Account[] acountArray = {
                new Account("Name1", new BigDecimal(3)),
                new Account("Name2", new BigDecimal(1)),
                new Account("Name3", new BigDecimal(2))
        };
        Arrays.sort(acountArray);   // sorts accountArray.  Needs  class Account implements Comparable<Account>

        printlnItterable(Arrays.asList(acountArray), "Sorted Array:");


        //COLLECTIONS

        //create
        Collection<Account> accountCollection = new ArrayList<Account>();
//        Arrays.asList(..., ..., ...); // -> List

        accountCollection.add(new Account("Name1", new BigDecimal(200)));
        accountCollection.add(new Account("Name2", new BigDecimal(400)));
        accountCollection.add(new Account("Name3", new BigDecimal(300)));
        accountCollection.add(new Account("Name4", new BigDecimal(100)));
//        accountCollection.add(new Account("Name5", null));

        printlnItterable(accountCollection, "accountCollection:");


        //copy list (elements are still references, only order and size can be changed)
        Collection<Account> newAccountCollection = new ArrayList<Account>(accountCollection);
        java.util.Collections.shuffle((List<? extends Comparable>)newAccountCollection);       // shuffles records order
        ((List<Account>) newAccountCollection).get(0).setBalance(new BigDecimal(999));    // will change balance to account that is present in both collections
        printlnItterable(newAccountCollection, "newAccountCollection:");


        // reverse List (Needs  class Account implements Comparable<Account>)
        java.util.Collections.reverse((List) accountCollection);  // (List) because only List has order
        printlnItterable(accountCollection, "reverse accountCollection:");



        // SORT LIST
        // when List Implements Comparable:
        java.util.Collections.sort((List<? extends Comparable>) accountCollection);   // sorts accountCollection.  Needs  class Account implements Comparable<Account>.   MyCollections.sort((List<Account>)
        printlnItterable(accountCollection, "sorted (implements Comparable) accountCollection:");


        // Sort with providing a compare predicate/here anonymous class  (Before Java 8)
//        ((List<Account>) newAccountCollection).sort(new Comparator<Account>() {
//            @Override
//            public int compare(Account o1, Account o2) {
//                return o1.getBalance().compareTo(o2.getBalance());
//            }
//        });

        // Sort with providing a lambda  (After Java 8)
        ((List<Account>) newAccountCollection).sort((Account o1, Account o2) -> {
                    return o1.getBalance().compareTo(o2.getBalance());
                }
        );
        // OR reference a method
        ((List<Account>) newAccountCollection).sort(Account::compareTo);   // or maybe   Account.COMPARE as a static compare method on same class
        // OR // directly specifying the method that returns the primitive type
        ((List<Account>) newAccountCollection).sort(Comparator.comparing(Account::getBalance));

        printlnItterable(newAccountCollection, "sorted (with Comparator) newAccountCollection:");





        // Search for a record (binarySearch requires sorted list. It searches in middle of list, then in middle of half, then ....)
        // Needs  class Account implements Comparable<Account>
        int position = java.util.Collections.binarySearch((List<Account>) accountCollection, new Account(null, new BigDecimal(300)) );   // (List) because only List has order
        System.out.println("0 based position of account with balance 300 is: "+position);


        //Find MIN, MAX
        // Needs  class Account implements Comparable<Account>
        Account min = java.util.Collections.min(accountCollection); // works on Collection because it does not need order
        Account max = java.util.Collections.max(accountCollection); // works on Collection because it does not need order
        System.out.println("Min balance is: "+min.getBalance()+". Max balance is: "+max.getBalance()+"\n");

    }

    private static void printlnItterable(Iterable<Account> acountArray, String message) {
        System.out.println(message);
        for (Account a : acountArray) {
            System.out.println("Account " + a.getName() + " has balance: " + a.getBalance());
        }
        System.out.println();
    }
}

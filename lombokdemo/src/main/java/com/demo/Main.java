package com.demo;

import lombok.Cleanup;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {
        @Cleanup  // will close Closable when block exits
                OutputStream outputStream = new FileOutputStream("out.tmp");
        outputStream.write(10001);

        Person person0 = new Person();
        System.out.println("Person0 with name: " + person0.getName());


        // constructor with name
        Person person1 = Person.createWithName("Name1", 44);
        System.out.println("Person1: ");
        System.out.println(person1);


        // dto with @Data and @AllArgsConstructor:
        Account account1 = new Account("Acc1", new BigDecimal(99), false);
        System.out.println("Account1 name: " + account1.getName());
        System.out.println("Account1:");
        System.out.println(account1);
        person1.addAccount(account1);


        // @EqualsAndHashCode and @ToString
        Person person21 = new Person("Name2", 44, new BigDecimal(300), false, new ArrayList<Account>());
        Person person22 = new Person("Name2", 44, new BigDecimal(300), false, new ArrayList<Account>());
        System.out.println("Person21 with name: " + person21.getName());
        System.out.println("Person21 equals Person22 ? : " + person21.equals(person22));

        person21.addAmount(new BigDecimal(44));


        // @Value
        Properties properties = new Properties("p1", 11);
        System.out.println("properties:");
        System.out.println(properties);
    }



}

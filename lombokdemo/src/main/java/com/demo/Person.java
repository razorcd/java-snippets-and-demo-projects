package com.demo;

import lombok.*;
import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor(staticName="createWithName")
@AllArgsConstructor()
@ToString
@EqualsAndHashCode
@Log  // adds local log prop. Also see @Log4j, ...
public class Person {

        static String type = "typeC";

        @Getter   // generates getter
        @Setter   // generates setter
        @NonNull  // only initialisation with null will throws NullPointerException. A NoArgsConstructor will work fine.
        private String name;

        @Getter @Setter
        @NonNull
        private int age;

        @Getter @Setter
        private BigDecimal amount;

        @Getter @Setter
        private boolean active;

        private List<Account> accounts = new ArrayList<Account>();


        public void addAmount(@NonNull BigDecimal newAmount) {
            log.info("Adding ammount " + newAmount);
            amount = amount.add(newAmount);
        }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}

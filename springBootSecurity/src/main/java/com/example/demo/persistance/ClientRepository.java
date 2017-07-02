package com.example.demo.persistance;

import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    public ClientEntity getClientByEmail(String email) {

        System.out.println(" !!!!! Fetching details for user: " + email);

        if (email.equals("user@example.com")) {
            return new ClientEntity("user@example.com", "uuu", new String[] {"ROLE_ANONYMOUS", "ROLE_USER"}, 20);
        }
        if (email.equals("admin@example.com")) {
            return new ClientEntity("admin@example.com", "aaa", new String[] {"ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN"}, 30);
        }
        return null;
    }
}

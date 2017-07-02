package com.example.demo.persistance;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

    @Id
    private int id;
    private int client_id;
    private String authority;

    public AuthorityEntity() {
    }

    public AuthorityEntity(int client_id, String authority) {
        this.client_id = client_id;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityEntity{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", authority='" + authority + '\'' +
                '}';
    }
}

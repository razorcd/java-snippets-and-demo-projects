package com.example.demo.persistance;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "clients")
public class ClientEntity implements UserDetails {

    @Id
    private int id;
    private String email;
    private String encryptedPassword;
    private int age;
    private boolean enabled;

    @OneToMany(mappedBy = "client_id" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorityEntities;

    public ClientEntity() {
    }

    public ClientEntity(String email, String encryptedPassword, int age, boolean enabled, List<AuthorityEntity> authorityEntities) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.age = age;
        this.enabled = enabled;
        this.authorityEntities = authorityEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthorityEntity> getAuthorityEntities() {
        return authorityEntities;
    }

    public void setAuthorityEntities(List<AuthorityEntity> authorityEntities) {
        this.authorityEntities = authorityEntities;
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", age=" + age +
                ", enabled=" + enabled +
                ", authorityEntities=" + authorityEntities +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // why is this `map` so slow:
        String[] array = getAuthorityEntities().stream().map(a -> a.getAuthority()).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(array);
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

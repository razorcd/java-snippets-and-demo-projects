package com.example.demo.config;

import com.example.demo.persistance.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ClientRepository clientRepository;


    // loads the User Object (this also contains the Principal Object)
    // So  Session  has  AuthenticationToken  has  UserDetails  has  Principal
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        // if ClientEntity does not implement UserDetails interface you need to create your UserDetails object
//        ClientEntity clientEntity = clientRepository.getClientByEmail(username);
//        return new User(clientEntity.getEmail(), clientEntity.getEncryptedPassword(), AuthorityUtils.createAuthorityList(clientEntity.getRoles()));

        // if ClientEntity implements UserDetails object then you can just return it
        return clientRepository.findByEmail(username);
    }
}

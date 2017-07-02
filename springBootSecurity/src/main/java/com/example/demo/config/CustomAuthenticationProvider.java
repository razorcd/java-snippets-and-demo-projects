package com.example.demo.config;

import com.example.demo.persistance.ClientEntity;
import com.example.demo.persistance.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    ClientRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Custom Authentication called when User tries to LOGIN
    // this will be called by the AuthenticationFilter
    // Check credentials here !
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // by default Spring is using the UsernamePasswordAuthenticationToken as Token in the Session object
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;   // because support method checks this

        // we are using our custom Authentication Token
        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;   // because support method checks this


        // load client by username
        ClientEntity clientEntity = repo.findByEmail(token.getName());


        // check user and password and other matching fields
        if (!Optional.ofNullable(clientEntity).isPresent() ||
                token.getAge() != clientEntity.getAge() ||   // authenticate only if user login form provides correct `age` value
//                !clientEntity.getPassword().equalsIgnoreCase(token.getCredentials().toString()) // plain text
                !passwordEncoder.matches(token.getCredentials().toString(), clientEntity.getPassword())  // using encoder
           ) {
            throw new BadCredentialsException("Invalid credentials.");
        }


        //log user in by returning the new Authorization object with the `clientEntity` included.
//        return new UsernamePasswordAuthenticationToken(clientEntity, clientEntity.getPassword(), clientEntity.getAuthorities());
        return new CustomAuthenticationToken(clientEntity, clientEntity.getPassword(), clientEntity.getAuthorities(), clientEntity.getAge());

    }




    // Checks the type of the Authentication Token
    @Override
    public boolean supports(Class<?> authenticationClass) {
//        return UsernamePasswordAuthenticationToken.class.equals(authenticationClass);
        return CustomAuthenticationToken.class.equals(authenticationClass);
    }
}

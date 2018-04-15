package com.example.demo.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        UserDocument user = userRepository.findByEmail(s);

//        if(user == null) {
//            throw new UsernameNotFoundException(String.format("The email %s doesn't exist", s));
//        }

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("USER"));
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        });

//        UserDetails userDetails = new org.springframework.security.core.userdetails.
//                User(s, "zzz", authorities);

//        return userDetails;
//        return new User("qewqweqwewqewqe", "zzz", true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
//        return new User(s, "zzz", true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
        String username = "user";
        if (s.equals(username)) {
            return new CustomUserDetails("9999999999999999999", username, "password", Arrays.asList("R1", "R2"));
        } else {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

    }
}

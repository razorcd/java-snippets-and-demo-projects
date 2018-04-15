package com.example.demo;

import com.example.demo.config.CustomUserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


    @RequestMapping("/principal")
    public Principal getPrincipal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }


    @RequestMapping("/principal2")
    public CustomUserDetails getPrincipal2(Principal principal) {
        return (CustomUserDetails) principal;
    }

    @RequestMapping("/principal3")
    public CustomUserDetails getPrincipal3(Authentication authentication) {
        return (CustomUserDetails) authentication.getPrincipal();
    }

    @RequestMapping("/authentication")
    public Authentication getAuth(Authentication authentication) {
        return authentication;
    }

    @RequestMapping("/privatepage")
	public String getTest() {
		return "private page accessed";
	}

    @RequestMapping("/publicpage")
    public String getTestPublic() {
        return "public page accessed";
    }
}

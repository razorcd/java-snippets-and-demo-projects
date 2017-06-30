package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
//@EnableWebSecurity
@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
    public String myPublic() {
	    return "index";
    }

    @RequestMapping("/anonymous")
    @PreAuthorize("isAnonymous()")   // only NOT authenticated users
    @ResponseBody
    public String anonymous() {
        return "Anonymous users only";
    }

    @RequestMapping("/mysecured")
    @PreAuthorize("isAuthenticated()")    // only authenticated users
//    @PreAuthorize("isFullyAuthenticated()")   // user has to be authenticated by real password and not by rememberMe.
//    @PreAuthorize("hasPermission()")   // with another method that resolves permissions
    public String mySecured() {
        return "mysecured";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")    // only users that have th
    public String admin() {
	    return "myadmin";
    }

}

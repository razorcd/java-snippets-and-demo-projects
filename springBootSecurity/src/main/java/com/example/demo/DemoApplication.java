package com.example.demo;

import com.example.demo.persistance.ClientEntity;
import com.example.demo.persistance.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
    ClientRepository clientRepository;

	@RequestMapping("/")
    public String myPublic() {
	    return "index";
    }

    @RequestMapping("/anonymous")
//    @PreAuthorize("isAnonymous()")   // only NOT authenticated users
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


    @RequestMapping(value = "/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object me() {

//        Authentication authenticationObj = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return authenticationObj;

        // or return Principal from the Authorization Token object
//        ClientEntity clientEntity = (ClientEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return clientEntity;

        // return the AuthorizationToken object from the Security context
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @RequestMapping("/logMeInAsUser")
    public String logMeInAsUser() {
	    ClientEntity clientEntity = clientRepository.getClientByEmail("user@example.com");

	    // set AuthenticationToken to the SecurityContext  (this will LOGIN a user)
	    Authentication auth = new UsernamePasswordAuthenticationToken(clientEntity, "uuu", clientEntity.getAuthorities());  // user / password is being checked
	    SecurityContextHolder.getContext().setAuthentication(auth);

	    return "redirect:/";
    }
}

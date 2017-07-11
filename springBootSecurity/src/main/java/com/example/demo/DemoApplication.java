package com.example.demo;

import com.example.demo.config.SecurityConfig;
import com.example.demo.persistance.ClientEntity;
import com.example.demo.persistance.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Role;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@EnableWebSecurity
@Import(SecurityConfig.class)
@Controller
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true) // enables: @PreAuthorize @PostAuthorize,   @RolesAllowed,    @Secured
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
    ClientRepository clientRepository;

	@Autowired
    PasswordEncoder passwordEncoder;


	// accessible form view template as  #{isAdmin}
	@ModelAttribute("isAdmin")
    public boolean isAdmin(Authentication authentication) {
	    return authentication != null &&
                authentication.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_ADMIN").get(0));
    }



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
//    @PreAuthorize("hasRole('ADMIN')")    // only users that have ADMIN role
    @RolesAllowed("ADMIN")     // needs to be enabled with EnableGlobalMethodSecurity   jsr250Enabled
//    @Secured("ADMIN")       // needs to be enabled with EnableGlobalMethodSecurity   secured
    public String admin() {
	    return "myadmin";
    }



    @RequestMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("isAuthenticated()")  // executed BEFORE method execution
    @PostAuthorize("principal.username==#model['client'].getUsername()")   // executed AFTER method execution  (here, can only see his own data)
//    @PostAuthorize("principal.username==returnObject.username")   //  access to return value of the method
    @ResponseBody
    public ClientEntity getClient(@PathVariable Integer id, Model model) {
        ClientEntity clientEntity = clientRepository.findById(id);
        model.addAttribute("client", clientEntity);
        return clientEntity;
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


    @RequestMapping(value = "/me2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ClientEntity me2(Authentication authentication) {
//   public ClientEntity me2(HttpSession session) {    // or inject the current Session object
        return (ClientEntity) authentication.getPrincipal();
    }



    @RequestMapping("/logMeInAsUser")
    public String logMeInAsUser() {
	    ClientEntity clientEntity = clientRepository.findByEmail("user@example.com");

	    // set AuthenticationToken to the SecurityContext  (this will LOGIN a user)
	    Authentication auth = new UsernamePasswordAuthenticationToken(clientEntity, null, clientEntity.getAuthorities());  // user / password is being checked
	    SecurityContextHolder.getContext().setAuthentication(auth);

	    return "redirect:/";
    }


    @RequestMapping("/prefilter")
    @PreFilter("filterObject > 3")    // filter the method argument List
    @ResponseBody
    public String prefilter(@RequestParam List<Integer> numbers) {
	    return "Filtered result: " + numbers.toString();
    }

    @RequestMapping("/postfilter")
    @PostFilter("filterObject > 3")  // filter the returned List
    @ResponseBody
    public List<Integer> postfilter() {
	    List<Integer> numbers = new ArrayList<Integer>(){{
	        add(1); add(2); add(3); add(4); add(5); add(6);
	    }};
        return numbers;
    }


}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//    @RequestMapping("/principal")
//    public Principal getPrincipal(Principal principal) {
//        return principal;
//    }

//    @RequestMapping("/privatepage")
//	public String getTest() {
//		return "private page accessed";
//	}

    @RequestMapping("/publicpage")
    public String getTestPublic() {
        return "public page accessed";
    }
}

package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SpringBootApplication
@RestController
@Api(value="theMainApi", description="Just a demo of the general paths. Deprecated!")
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


    @ApiOperation(value = "View a UserDto.", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 401, message = "Not authorized."),
            @ApiResponse(code = 403, message = "Forbidden."),
            @ApiResponse(code = 404, message = "Not found.")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDto getUser(@NotNull @Min(3) @PathVariable("id") int id) {
        return new UserDto("name1", 11, true);
    }


    /**
     * Create a User with all data.
     *
     * @param userDto the User object
     * @return the created User
     */
    @ApiOperation(value = "Create a UserDto.", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
    })
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userDto;
    }

}

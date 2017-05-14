package com.example.demo;

import com.example.demo.myConfigurations.MyConfigurationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.Map;

@SpringBootApplication  // equals: @EnableAutoConfiguration, @ComponentScan, @Configuration
//@Import(MyConfiguration.class) // specifically import a Component
//@ComponentScan // scan all classpath for Components and Import them in main context
@EnableConfigurationProperties(value = MyConfigurationProperty.class)
@ServletComponentScan // scans for servlet classes  like @WebFilter, @WebServlet, @WebListener.  If servlets, filters are not annotated with @ComponentxC
//@EnableResourceServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

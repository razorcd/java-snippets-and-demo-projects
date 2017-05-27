package com.example.demo;

import com.example.demo.myConfigurations.MyConfigurationProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication  // equals: @EnableAutoConfiguration, @ComponentScan, @Configuration
//@Import(MyConfiguration.class) // specifically import a Component
//@ComponentScan // scan all classpath for Components and Import them in main context
@EnableConfigurationProperties(value = MyConfigurationProperty.class)
@ServletComponentScan // scans for servlet classes  like @WebFilter, @WebServlet, @WebListener.  If servlets, filters are not annotated with @ComponentxC
//@EnableResourceServer
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        // Print all Beans in the context
//        for (String contextName : context.getBeanDefinitionNames()) {
//            System.out.println(contextName);
//        }
	}

}

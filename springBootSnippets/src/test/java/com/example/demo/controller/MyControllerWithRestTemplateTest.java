package com.example.demo.controller;

import com.example.demo.myConfigurations.localTimeFactory.LocalTimeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

// TESTS calling http requests with RestTemplate. Goes trough Spring Security.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyControllerWithRestTemplateTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    LocalTimeFactory localTimeFactory;


    // Mocking a Bean and testing endpoint result
    @Test
    public void testLocalTime() throws Exception {
        given(localTimeFactory.now()).willReturn(LocalDateTime.of(2018,1,18,1,5,54));
        String mockedTime = "2018-01-18T01:05:54<br/>2018-01-18T01:05:54";

        String response = testRestTemplate.getForObject("/localTime", String.class);

        assertEquals("Should return time from LocalTimeFactory", mockedTime, response);
    }

    @Test
    public void testGetRequestParams() throws Exception {

        String response = testRestTemplate.getForObject("/requestParam?myqueryParam=myqueryParamValue", String.class);

        assertThat(response, containsString("myqueryParamValue"));
        assertThat(response, not(containsString("123456789")));
    }

    @Test
    public void testGetPathParam() throws Exception{
        String response = testRestTemplate.getForObject("/pathParam/{myPathParam}", String.class, "myPathValue11"); // with separate path params

        assertThat(response, containsString("myPathValue11"));
    }

    @Autowired
    com.fasterxml.jackson.databind.ObjectMapper objectMapper;  // creates JSON format string
    @Test
    public void testGetResponseEntity() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("key1", "value1");

        String response = testRestTemplate.postForObject("/responseEntity", requestBody, String.class);

        assertEquals("Received Body: {key1=value1}", response);
    }
}
package com.example.demo.controller;

import com.example.demo.myConfigurations.localTimeFactory.LocalTimeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

// TESTS calling http requests. Goes trough Spring Security.
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc  // creates the MockMvc Bean in the context
public class MyControllerWithMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LocalTimeFactory localTimeFactory;


    // Mocking a Bean and testing endpoint result
    @Test
    public void testLocalTime() throws Exception {
        given(localTimeFactory.now()).willReturn(LocalDateTime.of(2018,1,18,1,5,54));
        String mockedTime = "2018-01-18T01:05:54<br/>2018-01-18T01:05:54";

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/localTime"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        assertEquals("Should return time from LocalTimeFactory", mockedTime, response);
    }

    @Test
    public void testGetRequestParams() throws Exception {
        mockMvc.perform(get("/requestParam")  // when MockMvcRequestBuilders is imported as static
                            .param("myqueryParam", "myqueryParamValue"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("myqueryParamValue")))
                .andExpect(content().string(not(containsString("123456789"))));
    }

    @Test
    public void testGetPathParam() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/pathParam/{myPathParam}", "myPathValue11"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("myPathValue11")));
    }

    @Autowired
    com.fasterxml.jackson.databind.ObjectMapper objectMapper;  // creates JSON format string
    @Test
    public void testGetResponseEntity() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("key1", "value1");

        mockMvc.perform(MockMvcRequestBuilders.post("/responseEntity")
                            .content(objectMapper.writeValueAsString(requestBody))
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Received Body: {key1=value1}"));
    }
}
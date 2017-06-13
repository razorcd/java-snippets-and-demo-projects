package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyErrorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void myCustomControllerException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/myCustomControllerException"))
                .andExpect(status().isIAmATeapot());
    }

    @Test
    public void myCustomControllerExceptionWithErrorAdvice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/myCustomControllerExceptionWithErrorAdvice"))
                .andExpect(status().isImUsed());
    }
}
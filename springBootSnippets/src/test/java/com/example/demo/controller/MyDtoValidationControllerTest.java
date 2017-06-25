package com.example.demo.controller;

import com.example.demo.controller.dto.DataValidatedDto;
import com.example.demo.controller.dto.SubDataValidatedDto;
import com.example.demo.myConfigurations.errorHandling.MyValidationErrorMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyDtoValidationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void postSomeValidData() throws Exception {
        DataValidatedDto data = new DataValidatedDto(
                "name1",
                "name@example.com",
                44,
                2.4,
                true,
                new Date(),
                new SubDataValidatedDto("username", "12345678")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/myDtoValidation")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsString(data)))
                .andExpect(content().string(containsString("The data you entered was valid")))
                .andExpect(status().is2xxSuccessful());

        // verify that mocked service is called
    }


    @Test
    public void postSomeInvalidData() throws Exception {
        DataValidatedDto data = new DataValidatedDto(
                "name1",
                "name@example.com",
                44,
                2.4,
                true,
                new Date(),
                new SubDataValidatedDto("", "12345678")
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/myDtoValidation")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(data)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("subData.username"));

        // verify that mocked service is NOT called

    }
}
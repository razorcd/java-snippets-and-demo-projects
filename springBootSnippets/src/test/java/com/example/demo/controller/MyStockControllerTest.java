package com.example.demo.controller;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyStockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
//    @SpyBean  // wraps a sly around it but does not mock
    StockService stockService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getStockByIdForNonExistentRecordTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stocks/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createStockTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/stocks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(new StockEntity("name1", "N1", 1.3, 1.6 ))))
                .andExpect(status().isCreated());

        Mockito.verify(stockService, Mockito.times(1))
                .createStock(Mockito.any(StockEntity.class));
    }

}

package com.example.demo.controller;

import com.example.demo.myConfigurations.localTimeFactory.LocalTimeFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyControllerTest {

    @Autowired
    MyController myController;

    @MockBean
    LocalTimeFactory localTimeFactory;

    @Test
    public void testLocalTime() throws InterruptedException{
        given(localTimeFactory.now()).willReturn(LocalDateTime.of(2018,1,18,1,5,54));
        String mockedTime = "2018-01-18T01:05:54<br/>2018-01-18T01:05:54";

        assertEquals("Should return time from LocalTimeFactory", mockedTime, myController.localTime());
    }
}
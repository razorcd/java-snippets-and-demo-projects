package com.example.demo.controller;

import com.example.demo.controller.dto.DataValidatedDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.invoke.empty.Empty;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class MyDtoValidationController {

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping(value = "/myDtoValidation")
    public String getSomeData(@RequestBody @Valid DataValidatedDto data) throws Exception {
        System.out.println(" ! The data was valid: ");
        System.out.println(objectMapper.writeValueAsString(data));

        HashMap<String, String> response = new HashMap<>();
        response.put("message", "The data you entered was valid. See server log for result.");

        return objectMapper.writeValueAsString(response);
    }
}

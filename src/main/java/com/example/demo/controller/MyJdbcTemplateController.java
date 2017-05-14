package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MyJdbcTemplateController {

    // JdbcTemplate is injected because it is present in the Context. By jdbc or data-jpa dependency
    @Autowired
    private JdbcTemplate template;

    @RequestMapping(value = "/jdbcTemplateData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Map<String, Object>> jdbcTemplateData(){
        return template.queryForList("select * from stocks");
    }
}

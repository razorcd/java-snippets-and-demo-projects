package com.example.demo.controller;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.persistanceSql.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@RestController
public class MySqlQueryController {

    // JdbcTemplate is injected because it is present in the Context. By jdbc or data-jpa dependency
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;

    @Autowired
    StockRepository stockRepository;

    @RequestMapping(value = "/jdbcTemplateData", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Map<String, Object>> jdbcTemplateData() {
        return jdbcTemplate.queryForList("select * from stocks");  // SQL lang
    }

    @RequestMapping(value = "/entityManagerData", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockEntity> entityManagerData() {
        return entityManager.createQuery("select s from StockEntity s").getResultList();  // JPQL lang
    }

    @RequestMapping(value = "/stockRepositoryData/{symbolParam}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StockEntity stockRepositoryData(@PathVariable("symbolParam") String symbolParam) {
        return stockRepository.findBySymbol(symbolParam);
    }
}

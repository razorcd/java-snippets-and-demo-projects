package com.example.demo.controller;

import com.example.demo.persistance.StockEntity;
import com.example.demo.persistance.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class MyStockController {

    @Autowired
    StockRepository stockRepository;

    @RequestMapping({"/stocks"})
    public List<StockEntity> myStocks() {
        return stockRepository.findAll();
    }
}
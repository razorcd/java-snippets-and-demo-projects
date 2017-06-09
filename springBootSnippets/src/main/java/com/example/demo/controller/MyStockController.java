package com.example.demo.controller;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stocks")
public class MyStockController {

    @Autowired
    StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createBookmark(@RequestBody StockEntity stockEntity) {
        stockService.createStock(stockEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

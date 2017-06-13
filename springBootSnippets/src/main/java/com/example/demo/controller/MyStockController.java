package com.example.demo.controller;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class MyStockController {

    @Autowired
    StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<StockEntity> getStock(@PathVariable int id) throws Exception {
//        return new ResponseEntity<>(stockService.getStockById(id), HttpStatus.OK);
        return new ResponseEntity<>(stockService.getStockById(id), HttpStatus.NOT_FOUND);  // broken, error not catched by Advice

    }

    @PostMapping
    public ResponseEntity<Void> createBookmark(@RequestBody StockEntity stockEntity) {
        stockService.createStock(stockEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

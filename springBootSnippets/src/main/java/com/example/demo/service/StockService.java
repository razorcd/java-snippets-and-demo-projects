package com.example.demo.service;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.persistanceSql.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public void createStock(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }

    public StockEntity getStockById(int id) throws Exception {
        if (!stockRepository.exists(id)) throw new NoSuchFieldException();
        return stockRepository.findOne(id);
    }
}

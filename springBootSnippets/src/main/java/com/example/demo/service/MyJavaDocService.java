/**
 * Copyright data !!!!
 */
package com.example.demo.service;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.persistanceSql.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Defines MyJavaDocService.
 * Lorem ipsum ....
 *
 * @author Cristian Dugacicu {@literal cristian.dugacicu@ubitricity.com}
 */
@Service
public class MyJavaDocService {

    private StockRepository stockRepository;

    @Autowired
    public MyJavaDocService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Get my data method example documentation.
     *
     * @param myDate an example variable
     * @return something
     */
    public List<StockEntity> getMyData(Date myDate) {
        return stockRepository.findAll();
    }

}

package com.example.demo.service;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.persistanceSql.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;

@Service
@Transactional
//@ConditionalOnProperty(name = "myCustom.stockservice", havingValue = "yes")   //  create `StockService` Bean only if `stockservice` property is set with `yes` value
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager; //because each datasource has it's own transaction manager


    public void createStock(StockEntity stockEntity) {
        stockRepository.save(stockEntity);
    }

    public StockEntity getStockById(int id) throws Exception {
        if (!stockRepository.exists(id)) throw new NoSuchFieldException();

        return stockRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true, timeout = 5000)
    public List<StockEntity> getStocks() {

        // TRANSACTION handling:
        TransactionInterceptor.currentTransactionStatus().setRollbackOnly(); // = when transaction is complete, rollback without failure
        platformTransactionManager.rollback(TransactionInterceptor.currentTransactionStatus());  // rollback now
        platformTransactionManager.commit(TransactionInterceptor.currentTransactionStatus());  // commit now

        return stockRepository.findAll();
    }
}


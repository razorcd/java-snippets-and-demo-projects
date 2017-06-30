package com.example.demo.service;

import com.example.demo.persistanceSql.StockEntity;
import com.example.demo.persistanceSql.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Service
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)   // each Bean autowiring is a new instance of this class
//@Scope(value = WebApplicationContext.SCOPE_REQUEST,
//        proxyMode = ScopedProxyMode.TARGET_CLASS)   // Bean is a different instance for each Request
//@Scope(value = WebApplicationContext.SCOPE_SESSION,
//        proxyMode = ScopedProxyMode.TARGET_CLASS)   // Bean is a different instance for each Session. (for session, bean must be serializable!)
//@Scope("websocket")   // Bean is different for each websocket
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


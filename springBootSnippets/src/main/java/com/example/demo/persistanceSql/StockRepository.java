package com.example.demo.persistanceSql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // works without this annotation
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

    public StockEntity findBySymbol(String symbol);

}

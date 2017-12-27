package com.example.demo.persistanceSql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // works without this annotation
public interface StockRepository extends JpaRepository<StockEntity, Integer> {
    List<StockEntity> findAll();
    public StockEntity findBySymbol(String symbol);
}

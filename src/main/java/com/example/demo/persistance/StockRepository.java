package com.example.demo.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

    public StockEntity findBySymbol(String symbol);

}

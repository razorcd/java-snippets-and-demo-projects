package com.example.demo.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "stocks", path = "stocks")
public interface StockRepository extends CrudRepository<StockEntity, String> {
//public interface StockRepository extends JpaRepository<StockEntity, String> {
//public interface StockRepository extends PagingAndSortingRepository<StockEntity, String> {


    List<StockEntity> findAll();

    StockEntity findBySymbol(@Param("symbol")String symbol);

    void deleteByStockId(String id);

}

package com.example.demo.persistanceSql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // works without this annotation
public interface StockCrudRepository extends CrudRepository<StockEntity, Integer> {

}

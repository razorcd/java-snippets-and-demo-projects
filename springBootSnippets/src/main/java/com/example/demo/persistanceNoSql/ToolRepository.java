package com.example.demo.persistanceNoSql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository  // works without this annotation
public interface ToolRepository extends MongoRepository<ToolDocument, Integer> {

    ToolDocument findBySymbol(String symbol);

}

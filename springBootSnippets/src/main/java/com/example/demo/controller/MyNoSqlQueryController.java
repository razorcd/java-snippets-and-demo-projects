package com.example.demo.controller;

import com.example.demo.persistanceNoSql.ToolDocument;
import com.example.demo.persistanceNoSql.ToolRepository;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RestController
public class MyNoSqlQueryController {

    @Autowired
    MongoDbFactory mongoDbFactory;

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    ToolRepository toolRepository;


    @RequestMapping(value = "/toolMongoDbFactoryData/{symbolParam}", produces = MediaType.APPLICATION_JSON_VALUE) //default GET
    public DBObject toolMongoDbFactoryData(@PathVariable("symbolParam") String symbolParam) {
        DB db = mongoDbFactory.getDb("spring_db_mongo");
        DBCollection dbCollection = db.getCollection("toolDocument");
        return dbCollection.findOne();
    }

    @RequestMapping(value = "/toolMongoTemplateData/{symbolParam}", produces = MediaType.APPLICATION_JSON_VALUE) //default GET
    public ToolDocument toolMongoTemplateData(@PathVariable("symbolParam") String symbolParam) {
//        DB db =mongoTemplate.getDb(); //takes db name from properties

        return mongoTemplate.findOne(new BasicQuery("{symbol: '" + symbolParam + "'}"), ToolDocument.class);
    }

    @RequestMapping(value = "/toolRepositoryData/{symbolParam}", produces = MediaType.APPLICATION_JSON_VALUE) //default GET
    public ToolDocument toolRepositoryData(@PathVariable("symbolParam") String symbolParam) {
        return toolRepository.findBySymbol(symbolParam);
    }

    @RequestMapping(value = "/toolRepositoryData/{symbolParam}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void postToolRepositoryData(@PathVariable("symbolParam") String symbolParam) {
        ToolDocument toolDocument = new ToolDocument();

        toolDocument.setToolId(UUID.randomUUID());
        toolDocument.setToolName("Tool000343");
        toolDocument.setSymbol(symbolParam);
        toolDocument.setPrice(100d);

        toolRepository.save(toolDocument);
    }

}

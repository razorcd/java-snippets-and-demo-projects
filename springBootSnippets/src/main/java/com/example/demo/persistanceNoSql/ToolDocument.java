package com.example.demo.persistanceNoSql;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Document
public class ToolDocument {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID toolId;

    private String toolName;

    private String symbol;

    private double price;

//    @SuppressWarnings("unused")
    public ToolDocument() {
    }

    @JsonCreator
    public ToolDocument(@JsonProperty("toolName") String toolName,
                        @JsonProperty("symbol") String symbol,
                        @JsonProperty("price") double price) {
        this.toolName = toolName;
        this.symbol = symbol;
        this.price = price;
    }

    public UUID getToolId() {
        return toolId;
    }

    public void setToolId(UUID toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

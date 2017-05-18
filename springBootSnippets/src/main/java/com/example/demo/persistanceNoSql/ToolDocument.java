package com.example.demo.persistanceNoSql;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class ToolDocument {

    @Id
    private int toolId;

    private String toolName;

    private String symbol;

    private double price;

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
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

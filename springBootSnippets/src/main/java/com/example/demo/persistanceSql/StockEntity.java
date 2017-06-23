package com.example.demo.persistanceSql;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_id")
    private int stockId;

    private String companyName;

    private String symbol;

    private double price;

    private double price2; //testing Hibernate ddl: update (here: add new column). Result: adds new column but does not remove it.

    // Optimistic Locking protection. Part of JPA. (will not allow updating with same Entity object twice)
    @Version
    private int version;


    @JsonCreator
    public StockEntity(@JsonProperty("stockId") int stockId,
                       @JsonProperty("companyName") String companyName,
                       @JsonProperty("symbol") String symbol,
                       @JsonProperty("price") double price,
                       @JsonProperty("price2") double price2) {
        this.stockId = stockId;
        this.companyName = companyName;
        this.symbol = symbol;
        this.price = price;
        this.price2 = price2;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public double getPrice2() {
        return price2;
    }

    public void setPrice2(double price2) {
        this.price2 = price2;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

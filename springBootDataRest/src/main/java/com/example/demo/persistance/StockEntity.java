package com.example.demo.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stocks")
public class StockEntity implements Serializable {

    @Id
    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private double price;

    public StockEntity() {
    }

    public StockEntity(String stockId, String companyName, String symbol, double price) {
        this.stockId = stockId;
        this.companyName = companyName;
        this.symbol = symbol;
        this.price = price;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
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
}

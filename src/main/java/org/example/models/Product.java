package org.example.models;

import org.example.utils.AppException;

import java.sql.Timestamp;

public class Product {
    private Timestamp id;
    private String title;
    private String desc;
    private double price;
    private int stocks;
    private Category categoryName;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws AppException {
        if (price <= 0) {
            throw new AppException("Price cannot be negative");
        }
        this.price = price;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }


}

package org.example.models;

import org.example.utils.AppException;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String title;
    private String desc;
    private double price;
    private int stocks;
    private int categoryId;

    public Product(int id, String title, String desc, double price, int stocks, int categoryId) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.stocks = stocks;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryName) {
        this.categoryId = categoryId;
    }


}

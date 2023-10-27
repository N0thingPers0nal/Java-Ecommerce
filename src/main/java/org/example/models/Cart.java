package org.example.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Cart {
    private Timestamp id;

    private ArrayList<CartProducts> cartProducts;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public ArrayList<CartProducts> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }
}

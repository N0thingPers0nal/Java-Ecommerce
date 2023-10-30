package org.example.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Cart {
    private User user;

    private ArrayList<CartProducts> cartProducts;
public Cart(){}
    public Cart(User user, ArrayList<CartProducts> cartProducts) {
    this.user=user;
    this.cartProducts=cartProducts;
    }

    public ArrayList<CartProducts> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<CartProducts> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

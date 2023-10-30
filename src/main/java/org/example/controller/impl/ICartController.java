package org.example.controller.impl;

import org.example.models.Product;

public interface ICartController {
    void addToCart(Product product);
    void showCart();
}

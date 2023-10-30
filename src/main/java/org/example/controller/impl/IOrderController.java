package org.example.controller.impl;

public interface IOrderController {
    void showOrders();
    void printBill();
    void saveFileNameInOrders(String fileName, String date);
}

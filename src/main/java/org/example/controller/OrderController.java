package org.example.controller;

import org.example.controller.impl.IOrderController;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;
import org.example.utils.UserUtil;
import org.example.view.OrderPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.UserUtil.getLoggedUser;

public class OrderController implements IOrderController {
    private final HomeController homeController;
    private final OrderPage orderPage;

    public OrderController(HomeController homeController) {
        this.homeController = homeController;
        orderPage = new OrderPage();
    }

    @Override
    public void showOrders() {
        ArrayList cart = ReadFilesUtils.readCartToOrders();
        if (cart.isEmpty()) {
            orderPage.noOrders();
        } else {
            orderPage.showOrders();
        }

        String dummy = enterString(StringUtil.ANYKEY);
        homeController.menu();

    }

    @Override
    public void printBill() {
        double total = 0;
        try {
            int s_no = 1;
            User user = getLoggedUser();
            String fileName = getFilePath() + "-OrderBill-" + getLoggedUser().getId() + "-" + System.currentTimeMillis() + ".txt";
            String date = date();
            saveFileNameInOrders(fileName, date);
            FileWriter fileWriter = new FileWriter(new File(fileName));
            fileWriter.append(StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "\t  Orders\t"+ StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + ".\n" + StringUtil.DOT + "\n");
            fileWriter.append("." + StringUtil.TAB + StringUtil.TAB +"Order Placed on : " + date+StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "\t.\n");
            fileWriter.append(StringUtil.CART_TITLE + "\n");
            for (Object order : ReadFilesUtils.readCartToOrders()) {
                String[] orderAr = order.toString().split(",");
                if (parseInt(orderAr[0]) == user.getId()) {
                    fileWriter.append("." + StringUtil.TAB + StringUtil.TAB + s_no++ + ". " + orderAr[4] + "\t" + orderAr[6] + "  x  " + orderAr[9] + "=   Rs. " + parseInt(orderAr[9]) * Double.parseDouble(orderAr[6]) + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "." + "\n");
                    total += parseInt(orderAr[8]) * Double.parseDouble(orderAr[6]);
                }
            }
            fileWriter.append(StringUtil.DOT + "\n" + "." + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "\tTotal= " + String.valueOf(total) + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + ".\n" + StringUtil.DOT);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getLoggedUser().setUserCart(null);
        orderPage.success();

    }

    @Override
    public void saveFileNameInOrders(String fileName, String date) {

        try {
            FileWriter fileWriter = new FileWriter(new File(getFilePath() + "orders.csv"), true);
            fileWriter.append(date + " - " + fileName + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}

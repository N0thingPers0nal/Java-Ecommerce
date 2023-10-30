package org.example.view;

import org.example.models.Cart;
import org.example.models.CartProducts;
import org.example.models.Product;
import org.example.models.User;
import org.example.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.ReadFilesUtils.getProductArr;
import static org.example.utils.ReadFilesUtils.getUsersArr;
import static org.example.utils.UserUtil.getLoggedUser;
import static org.example.utils.Utils.println;

public class CartPage {
    public void emptyCart() {
        println(StringUtil.EMPTY_CART);
        println(StringUtil.BACKFROMPRODUCTS);
        println(StringUtil.DOT);
    }
    public void showCart() {
        double total = 0;
        try {
            int s_no = 1;
            User user = getLoggedUser();
            println(StringUtil.CART_TITLE);
            Scanner sc = new Scanner(new File(getFilePath() + "cart.csv"));
            while (sc.hasNext()) {
                String line = sc.next();
                String[] cartAr = line.split(",");
                if(parseInt(cartAr[0])==user.getId()) {
                    System.out.println("." + StringUtil.TAB + StringUtil.TAB + s_no++ + ". " + cartAr[4] + "\t" + cartAr[6] + "  x  " + cartAr[9] + "=   Rs. " + parseInt(cartAr[9]) * Double.parseDouble(cartAr[6]) + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + ".");
                    total += parseInt(cartAr[9]) * Double.parseDouble(cartAr[6]);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        println("TOTAL=" + total);
        println(StringUtil.CHECKOUT);
        println(StringUtil.BACKFROMPRODUCTS);
        println(StringUtil.DOT);
    }




}

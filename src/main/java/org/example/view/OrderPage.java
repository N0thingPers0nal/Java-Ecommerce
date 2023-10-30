package org.example.view;

import org.example.models.User;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static org.example.utils.ReadFilesUtils.getUsersArr;
import static org.example.utils.UserUtil.getLoggedUser;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class OrderPage {
    public void noOrders() {
        println(StringUtil.EMPTY_ORDERS);
    }

    public void showOrders() {
        double total = 0;
        int s_no = 1;
        User user = getLoggedUser();
        print(StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "\tOrders\n" + StringUtil.DOT + "\n");
        print(StringUtil.CART_TITLE + "\n");
        for (Object order : ReadFilesUtils.readCartToOrders()) {
            String[] orderAr = order.toString().split(",");
           if(parseInt(orderAr[0])==user.getId()){
               print("." + StringUtil.TAB + StringUtil.TAB + s_no++ + ". " + orderAr[4] + "\t" + orderAr[6] + "  x  " + orderAr[9] + "=   Rs. " + parseInt(orderAr[9]) * Double.parseDouble(orderAr[6]) + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "." + "\n");
               total += parseInt(orderAr[9]) * Double.parseDouble(orderAr[6]);
           }
        }
        println(StringUtil.DOT + "\n" + "." + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "\tTotal= " + String.valueOf(total) + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + ".\n" + StringUtil.DOT + "\n");
    }

    public void success() {
        println(StringUtil.DOT);
        println(StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + "Order Placed Successfully" + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB + StringUtil.TAB);
        println(StringUtil.DOT);
    }
}

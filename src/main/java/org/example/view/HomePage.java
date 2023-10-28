package org.example.view;

import org.example.utils.FileUtil;
import org.example.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.Utils.println;

public class HomePage {
    public void menuProducts() {
        println(StringUtil.PRODUCTS);
    }

    public void userOptions() {
        println(StringUtil.USEROPTIONS);
    }

    public void logoutSuccess() {
        println(StringUtil.LOGOUT_SUCCESSFULLY);
    }

//    public void viewCategories() {
//        try {
//            Scanner sc = new Scanner(new File(getFilePath() + "category.csv"));
//            while (sc.hasNext()) {
//                String[] categoryArr = sc.next().split(",");
//                println(categoryArr[0] + ". " + categoryArr[1]);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

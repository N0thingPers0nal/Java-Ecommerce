package org.example.utils;

import org.example.models.Category;
import org.example.models.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.example.utils.FileUtil.getFilePath;

public class ReadFilesUtils {
    private static final ArrayList<Category> categoryArr = new ArrayList();
    private static final ArrayList<Product> productArr=new ArrayList<>();

    public static void category() {
        if(categoryArr.size()==0){
            try {
                Scanner sc = new Scanner(new File(getFilePath() + "category.csv"));
                while (sc.hasNext()) {
                    String[] categoryAr = sc.next().split(",");
                    Category category = new Category(parseInt(categoryAr[0]), categoryAr[1]);
                    categoryArr.add(category);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void product() {
        if(productArr.size()==0){
            try {
                Scanner sc = new Scanner(new File(getFilePath() + "products.csv"));
                while (sc.hasNext()) {
                    String[] productAr = sc.next().split(",");
                    Product product = new Product(parseInt(productAr[0]),productAr[1],productAr[2],Double.parseDouble(productAr[3]),parseInt(productAr[4]),parseInt(productAr[5]));
                    productArr.add(product);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<Category> getCategoryArr() {
        return categoryArr;
    }

    public static ArrayList<Product> getProductArr() {
        return productArr;
    }
}

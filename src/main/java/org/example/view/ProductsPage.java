package org.example.view;

import org.example.models.Product;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;


import java.util.ArrayList;

import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class ProductsPage {
    public ProductsPage() {
        ReadFilesUtils.product();
    }

    public void title() {
        println(StringUtil.PRODUCTS);
    }
    public void viewProduct(ArrayList<Product> products) {
        int i=1;
        for (Product product :products) {
            println("." + StringUtil.TAB + product.getId() + ". " + product.getTitle() + "\t" + product.getDesc() + "\tRs. " + product.getPrice() + "\t" + product.getStocks() + StringUtil.TAB + ".");
        }
        println(StringUtil.BACKFROMPRODUCTS);
        println(StringUtil.DOT);
//        try {
//            Scanner sc = new Scanner(new File(getFilePath() + "products.csv"));
//            while (sc.hasNext()) {
//                String[] productsArr = sc.next().split(",");
//
//                if (id == 0) {
//                    for (int i = 1; i <= 4; i++) {
//                        p += productsArr[i] + "\t";
//                    }
//                } else {
//                    for (int i = 1; i <= 4; i++) {
//                        if (id == parseInt(productsArr[0])) {
//                            a = 1;
//                            p += productsArr[i] + "\t";
//                        }
//                    }
//                }
//                if (a == 1 && id != 0) {
//                    println("." + StringUtil.TAB + productsArr[0] + ". " + p + StringUtil.TAB + "\t" + ".");
//                    a = 0;
//                } else if (a == 0 && id == 0) {
//                    println("." + StringUtil.TAB + productsArr[0] + ". " + p + StringUtil.TAB + "\t" + ".");
//                }
//            }
//            println(StringUtil.BACKFROMPRODUCTS);
//            sc.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void successToCart(String product){

        println(product+" "+StringUtil.ADD_TO_CART);
    }
}

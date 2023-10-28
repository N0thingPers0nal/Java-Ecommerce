package org.example.controller;

import org.example.controller.impl.IProductController;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.StringUtil;
import org.example.view.HomePage;
import org.example.view.ProductsPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.ReadFilesUtils.getProductArr;
import static org.example.utils.Utils.println;

public class ProductController implements IProductController {
    private final HomeController homeController;
    //    private final AuthController authController;
    private final ProductsPage productsPage;

    public ProductController(HomeController homeController) {
        this.homeController = homeController;
        productsPage = new ProductsPage();
    }

    private final int id = 0;

    @Override
    public void productTitle() {
        productsPage.title();
    }

    public void showProducts(int categoryId) {
        ArrayList<Product> productsArr = getProductArr();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : productsArr) {
                if (product.getCategoryId() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            productsArr= categoryProducts;
        }
        productTitle();
        productsPage.viewProduct(productsArr);

        try {
            int choice = enterInt(StringUtil.CHOICE);
            if (choice == 9) {
                homeController.menu();
            } else {
                showProducts(choice);
            }
            if (categoryId == 0) {

            } else {
                invalid(new AppException(StringUtil.INVALID));
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }

    }

    private void invalid(AppException appException) {
        println(appException.getMessage());
    }

}

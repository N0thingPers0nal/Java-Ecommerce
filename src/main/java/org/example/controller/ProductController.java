package org.example.controller;

import org.example.controller.impl.IProductController;
import org.example.models.Product;
import org.example.utils.AppException;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;
import org.example.view.CategeriesPage;
import org.example.view.HomePage;
import org.example.view.ProductsPage;

import java.util.ArrayList;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.ReadFilesUtils.getProductArr;
import static org.example.utils.Utils.println;

public class ProductController implements IProductController {
    private final HomeController homeController;
        private final CartController cartController;
    private final ProductsPage productsPage;
    private final CategeriesPage categeriesPage;

    public ProductController(HomeController homeController) {
        this.homeController = homeController;
        productsPage = new ProductsPage();
        categeriesPage =new CategeriesPage();
        cartController=new CartController(homeController);
    }

    private static int categoryId = 0;

    @Override
    public void productTitle() {
        productsPage.title();
    }

    @Override
    public void showProducts(int categoryId) {

        this.categoryId=categoryId;
        ArrayList<Product> productsArr = getProductArr();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : productsArr) {
                if (product.getCategoryId() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            productsArr = categoryProducts;
        }

        if (!productsArr.isEmpty()) {
            productTitle();
            productsPage.viewProduct(productsArr);
        } else {
            categeriesPage.categoryNotFound();
        }
        try {
            int choice = enterInt(StringUtil.CHOICE);
            int productId=0;
            Product p = null;
            String productTitle="";
            if (choice == 9) {
                homeController.menu();
            } else {
                for (Product product : productsArr) {
                    if (product.getId() == choice) {
                        p=product;
                        productId = product.getId();
                        productTitle=product.getTitle();
                        break;
                    }
                }
            }
            if (productId != 0) {
                cartController.addToCart(p);
                productsPage.successToCart(productTitle);

            } else {
                invalid(new AppException(StringUtil.PRODUCT_NOT_FOUND));
            }showProducts(categoryId);
        } catch (AppException appException) {
            invalid(appException);
        }

    }


    private void invalid(AppException appException) {
        println(appException.getMessage());
        showProducts(categoryId);
    }

}

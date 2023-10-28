package org.example.controller;

import org.example.controller.impl.IHomeController;
import org.example.utils.AppException;
import org.example.utils.StringUtil;
import org.example.view.CategeriesPage;
import org.example.view.HomePage;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.Utils.println;

public class HomeController implements IHomeController {
    private final HomePage homePage;
    private final AuthController authController;
    private final CategoriesController categoriesController;
    private final ProductController productController;

    public HomeController(AuthController authController) {
        homePage = new HomePage();
        this.authController=authController;
        categoriesController=new CategoriesController(this);
        productController=new ProductController(this);
    }

    @Override
    public void menu() {
        homePage.userOptions();
        int choice = 0;
        try {
            choice = enterInt(StringUtil.CHOICE);
            if (choice == 1) {
                categoriesController.printCatogries();
            } else if (choice == 2) {
                productController.showProducts(0);
            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {
                logout();
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

    @Override
    public void logout() {

        try {
            homePage.logoutSuccess();
            Thread.sleep(500);
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

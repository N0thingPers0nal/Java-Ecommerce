package org.example.controller;

import org.example.controller.impl.ICategoriesController;
import org.example.utils.AppException;
import org.example.utils.StringUtil;
import org.example.view.CategeriesPage;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.Utils.println;

public class CategoriesController implements ICategoriesController {
    private final CategeriesPage categeriesPage;
    private final HomeController homeController;
    private final ProductController productController;

    public CategoriesController(HomeController homeController) {
        categeriesPage=new CategeriesPage();
        this.homeController=homeController;
        productController=new ProductController(homeController);
    }

    @Override
    public void printMenu() {
        categeriesPage.viewCategories();
    }

    @Override
    public void printCatogries() {
        printMenu();
        int choice = 0;
        try {
            choice = enterInt(StringUtil.CHOICE);
            if (choice == 9) {
                homeController.menu();
            } else { int validCategoryId = 0;
                productController.showProducts(choice);
            if(validCategoryId!=0){
                println("Test");
            }
            else {
                invalid(new AppException(StringUtil.INVALID));
            }}
        } catch (AppException e) {
            throw new RuntimeException(e);
        }

    }

    private void invalid(AppException appException) {
        println(appException.getMessage());
    }
}

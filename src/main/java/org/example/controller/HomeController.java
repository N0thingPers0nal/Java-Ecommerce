package org.example.controller;

import org.example.controller.impl.IHomeController;
import org.example.utils.AppException;
import org.example.utils.StringUtil;
import org.example.view.HomePage;

import static org.example.utils.AppInput.enterInt;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class HomeController implements IHomeController {
    private final HomePage homePage;

    public HomeController() {
        homePage = new HomePage();
    }

    @Override
    public void menu() {
        homePage.userOptions();
        int choice = 0;
        try {
            choice = enterInt(StringUtil.CHOICE);
            if (choice == 1) {
                homePage.menuProducts();
            } else if (choice == 2) {
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

package org.example.controller;

import org.example.controller.impl.IAuthController;
import org.example.models.Role;
import org.example.models.User;
import org.example.utils.AppException;
import org.example.utils.HistoryUtils;
import org.example.utils.StringUtil;
import org.example.view.LoginPage;
import org.example.view.RegisterPage;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.example.utils.AppInput.enterInt;
import static org.example.utils.AppInput.enterString;
import static org.example.utils.FileUtil.getCredentialFile;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.UserUtil.setLoggedUser;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class AuthController implements IAuthController {
    private static int regId = HistoryUtils.userId() + 1;

    private final AppController appController;
    private final HomeController homeController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;


    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
    }

    @Override
    public void authServ() {
        appController.printAuthServ();
        int choice = 0;
        try {
            choice = enterInt(StringUtil.CHOICE);
            if (choice == 1) {
                register();
            } else if (choice == 2) {
                login();
            } else if (choice == 3) {
                System.exit(0);
            } else {
                invalid(new AppException(StringUtil.INVALID));
            }

        } catch (AppException appException) {
            invalid(appException);
        }

    }

    private void invalid(AppException appException) {
        println(appException.getMessage());
        authServ();
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtil.EMAIL);
        password = enterString(StringUtil.PASSWORD);
        User user = validateUser(email, password);
        if (user == null) {
            loginPage.fail();
            authServ();
        } else {
            loginPage.success();
            setLoggedUser(user);
            homeController.menu();
//            homePage.menuProducts();
        }
    }

    private User validateUser(String email, String password) {

        try {
            Scanner sc = new Scanner(getCredentialFile());
            while (sc.hasNext()) {
                String[] userArr = sc.next().split(",");
                if (email.equals(userArr[2]) && password.equals(userArr[3])) {
                    User user = new User();
                    user.setId(parseInt(userArr[0]));
                    user.setName(userArr[1]);
                    user.setEmail(email);
                    user.setPassword(password);
                    if (user.getName().equals("admin")) {
                        user.setRole(Role.ADMIN);
                    } else {
                        user.setRole(Role.USER);
                    }
                    return user;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void register() {
        String name, email, password, confirm_password;
        name = enterString(StringUtil.NAME);
        email = enterString(StringUtil.EMAIL);
        password = enterString(StringUtil.PASSWORD);
        confirm_password = enterString(StringUtil.CONFIRM_PASSWORD);
        if (password.equals(confirm_password)) {
            try {
                FileWriter csvWriter = new FileWriter(getFilePath() + "credentials.csv", true);
                csvWriter.append(regId + "," + name + "," + email + "," + password + "\n");
                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            regId += 1;
            registerPage.success();
        } else {
            registerPage.fail();
        }
        authServ();
    }

    @Override
    public void logout() {
    }
}

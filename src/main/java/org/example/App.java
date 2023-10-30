package org.example;

import org.example.controller.AppController;
import org.example.utils.ReadFilesUtils;

import static org.example.utils.ReadFilesUtils.getUsersArr;
import static org.example.utils.UserUtil.setLoggedUser;

public class App {

    public static void main(String[] args) {
        ReadFilesUtils.users();
        setLoggedUser(getUsersArr().get(2));
        ReadFilesUtils.product();

        ReadFilesUtils.cart();
        AppController appController=new AppController();
        appController.init();

    }
}
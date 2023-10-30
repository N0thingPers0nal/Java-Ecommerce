package org.example.controller;

import org.example.controller.impl.ICartController;
import org.example.models.*;
import org.example.utils.AppException;
import org.example.utils.ReadFilesUtils;
import org.example.utils.StringUtil;
import org.example.view.CartPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.example.utils.AppInput.enterInt;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.ReadFilesUtils.*;
import static org.example.utils.UserUtil.getLoggedUser;
import static org.example.utils.UserUtil.setLoggedUser;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class CartController implements ICartController {
    private final HomeController homeController;
    private final CartPage cartPage;
    private final OrderController orderController;

    public CartController(HomeController homeController) {
        this.homeController = homeController;
        cartPage = new CartPage();
        orderController = new OrderController(homeController);
    }

    @Override
    public void addToCart(Product selectedProduct) {
        User user = getLoggedUser();
//        try1();
        if (getLoggedUser().getUserCart() == null) {
            Cart cart = new Cart();
            ArrayList<CartProducts> cartProducts = new ArrayList<>();
            cartProducts.add(new CartProducts(selectedProduct, 1));
            cart.setCartProducts(cartProducts);
//            System.out.println(cart.getCartProducts().get(1));
            getLoggedUser().setUserCart(cart);
        } else {
            Cart cart = getLoggedUser().getUserCart();
            boolean isFound = false;
            for (CartProducts cartProduct : cart.getCartProducts()) {
                if (cartProduct.getProduct().getId() == selectedProduct.getId()) {
                    cartProduct.setCount(cartProduct.getCount() + 1);
//                    System.out.println(cartProduct.getCount());
                    isFound = true;
                }
            }
            if (!isFound) {
                cart.getCartProducts().add(new CartProducts(selectedProduct, 1));
            }
            getLoggedUser().setUserCart(cart);
        }

//        user.getUserCart().getCartProducts()

        setLoggedUser(getLoggedUser());
        ReadFilesUtils.writeCart();

    }


    @Override
    public void showCart() {
        if (getLoggedUser().getUserCart() != null) {
            cartPage.showCart();
        } else {
            cartPage.emptyCart();
        }

        try {
            int choice = enterInt(StringUtil.CHOICE);
            if (choice == 9) {
                homeController.menu();
            } else if (choice == 8) {
                orderController.printBill();
                homeController.menu();
            } else {
                invalid(new AppException(StringUtil.INVALID));
            }

        } catch (AppException appException) {
            invalid(appException);
        }
    }

    private void invalid(AppException appException) {
        println(appException.getMessage());
        showCart();
    }


}


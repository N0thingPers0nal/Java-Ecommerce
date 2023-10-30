package org.example.utils;

import org.example.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static org.example.utils.FileUtil.getFilePath;
import static org.example.utils.UserUtil.getLoggedUser;
import static org.example.utils.Utils.println;

public class ReadFilesUtils {
    private static final ArrayList<Category> categoryArr = new ArrayList<>();
    private static final ArrayList<Product> productArr = new ArrayList<>();
    private static final ArrayList<User> usersArr = new ArrayList<>();
    private static final ArrayList<Cart> cartArr = new ArrayList<>();

    public static void users() {
        try {
            Scanner sc = new Scanner(new File(getFilePath() + "credentials.csv"));
            while (sc.hasNext()) {
                String[] userAr = sc.next().split(",");
                User user = new User(parseInt(userAr[0]), userAr[1], userAr[2], userAr[3]);
                usersArr.add(user);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cart() {
        try {
            User user = null;
            Product product = null;
            int s_no = 1;
            ArrayList cartA = ReadFilesUtils.readCartToOrders();

            ArrayList<User> users = getUsersArr();
            ArrayList<Product> products = getProductArr();

            Scanner sc = new Scanner(new File(getFilePath() + "cart.csv"));
            while (sc.hasNext()) {
                String line = sc.next();
                String[] cartAr = line.split(",");
                System.out.println(line);
                for (User u : users) {
                    if (Objects.equals(u.getName(), cartAr[1])) {
                        user = u;
                        break;
                    }
                }
                for (Product p : products) {
                    if (p.getId() == parseInt(cartAr[3])) {
                        product = p;
                        break;
                    }
                }
                ArrayList<CartProducts> cartProducts = new ArrayList<>();
                cartProducts.add(new CartProducts(product, parseInt(cartAr[8])));
                Cart cart = new Cart(user, cartProducts);
                user.setUserCart(cart);
                try1();


//                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        println(StringUtil.CHECKOUT);
//        println(StringUtil.BACKFROMPRODUCTS);
//        println(StringUtil.DOT);

    }

    public static void try1() {
        ArrayList<User> users = getUsersArr();
        for (User user : users) {
            if(user.getUserCart()!=null){
                System.out.println(user.getName() + "--------------");
                for (CartProducts cartProducts1 : getLoggedUser().getUserCart().getCartProducts()) {
                    System.out.println(cartProducts1.getProduct().getTitle());
                }
            }
        }
    }

    public static void category() {
        if (categoryArr.isEmpty()) {
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
        if (productArr.isEmpty()) {
            try {
                Scanner sc = new Scanner(new File(getFilePath() + "products.csv"));
                while (sc.hasNext()) {
                    String[] productAr = sc.next().split(",");
                    Product product = new Product(parseInt(productAr[0]), productAr[1], productAr[2], parseDouble(productAr[3]), parseInt(productAr[4]), parseInt(productAr[5]));
                    productArr.add(product);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList readCartToOrders() {
        ArrayList cart = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(getFilePath() + "cart.csv"));
            while (sc.hasNext()) {
                cart.add(sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    public static void writeCart() {

        try {
            FileWriter fileWriter = new FileWriter(getFilePath() + "cart.csv");
            cart();
            User user = getLoggedUser();
//            CartProducts cartProducts=user.getUserCart().getCartProducts();

//            for (int i = 0; i < cart.size(); i++) {
//                ArrayList cartItem = (ArrayList) cart.get(i);
//                for (int j = 0; j < cartItem.size(); j++) {
//                    if (user.getId() == Integer.parseInt(String.valueOf(cartItem.get(0))) && product.getId() == parseInt(cartItem.get(2).toString())) {
////                        System.out.println(cart.get(i) + " ");
////                        fileWriter.append(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + product.getId() + "," + product.getTitle() + "," + product.getPrice() + "," + product.getStocks() + "," + user.getUserCart().getCartProducts() + "\n");
//                        break;
//                    }
//                    else{
//
//                    }
//                }
//            }
            for (CartProducts product : user.getUserCart().getCartProducts()) {
                fileWriter.append(user.getId() + "," + user.getName() + "," + user.getEmail() + "," + product.getProduct().getId() + "," + product.getProduct().getTitle() + "," + product.getProduct().getDesc() + "," + product.getProduct().getPrice() + "," + product.getProduct().getStocks() + "," + product.getProduct().getCategoryId() + "," + product.getCount() + "\n");

            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<User> getUsersArr() {
        return usersArr;
    }

    public static ArrayList<Category> getCategoryArr() {
        return categoryArr;
    }

    public static ArrayList<Product> getProductArr() {
        return productArr;
    }


}

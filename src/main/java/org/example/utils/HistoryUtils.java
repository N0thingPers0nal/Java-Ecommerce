package org.example.utils;

import org.example.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static org.example.utils.FileUtil.getFilePath;

public class HistoryUtils {
    private static final ArrayList<User> users = new ArrayList<>();

    public static int userId() {
        int id = 1;
        try {
            Scanner scanner = new Scanner(new File(getFilePath() + "credentials.csv"));
            while (scanner.hasNext()) {
                String[] user = scanner.next().split(",");
                id = parseInt(user[0]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


}

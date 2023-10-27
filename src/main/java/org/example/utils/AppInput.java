package org.example.utils;

import java.util.InputMismatchException;

import static org.example.utils.AppScanner.getScanner;
import static org.example.utils.Utils.print;
import static org.example.utils.Utils.println;

public class AppInput {
    public static String enterString(String msg) {
        print(msg);
        return getScanner().nextLine();
    }

    public static int enterInt(String msg) throws AppException {
        print(msg);
        int input;
        try {
            input = Integer.parseInt(getScanner().nextLine());
        } catch (Exception e) {
            throw new AppException(StringUtil.INVALID);
        }
        return input;
    }
}

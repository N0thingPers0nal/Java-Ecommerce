package org.example.view;

import org.example.utils.StringUtil;

import static org.example.utils.Utils.println;

public class LoginPage {
    public void success() {println(StringUtil.SUCCESS);
//        try {
//            Thread.sleep(500);
//            println(StringUtil.SUCCESS);
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void fail() {
        println(StringUtil.INVALIDCREDENTIALS);
//        try {
//            Thread.sleep(500);
//            println(StringUtil.INVALIDCREDENTIALS);
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}

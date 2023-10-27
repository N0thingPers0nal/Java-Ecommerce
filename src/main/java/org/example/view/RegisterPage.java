package org.example.view;

import org.example.utils.StringUtil;

import static org.example.utils.Utils.println;

public class RegisterPage {
    public void success() {
        try {
            println(StringUtil.SUCCESS);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void fail(){
        try {
            println(StringUtil.PASSWORDMISMATCH);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

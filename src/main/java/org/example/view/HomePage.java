package org.example.view;

import org.example.utils.StringUtil;

import static org.example.utils.Utils.println;

public class HomePage {
    public void menuProducts(){
        println(StringUtil.PRODUCTS);
    }
    public void userOptions(){println(StringUtil.USEROPTIONS);}
    public void logoutSuccess(){println(StringUtil.LOGOUT_SUCCESSFULLY);}
}

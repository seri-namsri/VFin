package com.mvision.vfin.error;

/**
 * Created by enter_01 on 12/8/2017 AD.
 */

public class ErrorCode {
    private static ErrorCode instance = null;

    public static ErrorCode getInstance() {
        if (instance == null)
            instance = new ErrorCode();

        return instance;
    }

    public String reload = "001";
    public boolean getContect(String msg){
        if (msg.equals(reload)){
            return true;
        }
        return false;
    }

}

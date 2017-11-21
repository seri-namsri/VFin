package com.example.enter_01.vfin.api.modelrequest;

import com.example.enter_01.vfin.utility.UtilsEncoding;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class LoginRequestModel {
    private String userName,password;
    public LoginRequestModel(String userName,String password){
        this.userName = userName;
        this.password = UtilsEncoding.SHA1(password);
    }
}

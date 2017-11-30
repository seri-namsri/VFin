package com.mvision.vfin.api.modelrequest;


import com.google.gson.Gson;
import com.mvision.vfin.utility.Log;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class UpdateProfileRequestModel {


    public UpdateProfileRequestModel(String type, MemberUpdate member) {
        this.type = type;
        this.member = new Gson().toJson(member);
    }

    private String type;
    private String member;

}
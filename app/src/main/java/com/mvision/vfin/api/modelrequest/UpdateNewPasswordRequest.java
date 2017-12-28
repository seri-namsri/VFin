package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class UpdateNewPasswordRequest {
    @SerializedName("mobilePhoneNo")
    private String mobilePhoneNo;
    @SerializedName("password")
    private String password;
    public UpdateNewPasswordRequest(String mobilePhoneNo,String password){
        this.mobilePhoneNo = mobilePhoneNo;
        this.password = password;
    }
}

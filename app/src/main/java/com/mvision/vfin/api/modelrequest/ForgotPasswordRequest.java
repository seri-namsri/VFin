package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class ForgotPasswordRequest {
    @SerializedName("mobilePhoneNo")
    private String mobilePhoneNo;
    @SerializedName("otpType")
    private String otpType ;
    public ForgotPasswordRequest(String mobilePhoneNo,String otpType){
        this.mobilePhoneNo = mobilePhoneNo;
        this.otpType = otpType;
    }
}
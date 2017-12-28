package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.UtilsEncoding;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class VerifyOtpRequest {
    @SerializedName("mobilePhoneNo")
    private String mobilePhoneNo;
    @SerializedName("otpCode")
    private String otpCode;
    @SerializedName("otpType")
    private String otpType;
    @SerializedName("refCode")
    private String refCode;
    @SerializedName("password")
    private String password;
    public VerifyOtpRequest(String mobilePhoneNo,String otpCode,String refCode,String password){
        this.mobilePhoneNo = mobilePhoneNo;
        this.otpCode = otpCode;
        this.otpType = "forgotPassword";
        this.refCode = refCode;
        this.password = UtilsEncoding.SHA1(password);
    }
}

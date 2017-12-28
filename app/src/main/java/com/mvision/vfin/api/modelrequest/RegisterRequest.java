package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.BuildConfig;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.Utility;
import com.mvision.vfin.utility.UtilsEncoding;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class RegisterRequest {
    public RegisterRequest(String email, String facebookId, String mobilePhoneNo, String otpCode, String password, String refCode) {
        this.email = email;
        this.deviceId = Utility.getDeviceID(Contextor.getInstance().getContext());
        this.facebookId = facebookId;
        this.imei = Utility.getIMEI1(Contextor.getInstance().getContext());
        this.imei2 = Utility.getIMEI2(Contextor.getInstance().getContext());
        this.mobilePhoneNo = mobilePhoneNo;
        this.otpCode = otpCode;
        this.password = UtilsEncoding.SHA1(password);
        this.platform = "android";
        this.refCode = refCode;
        this.simNo = Utility.getSimNumber(Contextor.getInstance().getContext());
        this.versionCode = String.valueOf(BuildConfig.VERSION_CODE);
    }

    @SerializedName("email")
    private String email;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("facebookId")
    private String facebookId;
    @SerializedName("imei")
    private String imei;
    @SerializedName("imei2")
    private String imei2;
    @SerializedName("mobilePhoneNo")
    private String mobilePhoneNo;
    @SerializedName("otpCode")
    private String otpCode;
    @SerializedName("password")
    private String password;
    @SerializedName("platform")
    private String platform;
    @SerializedName("refCode")
    private String refCode;
    @SerializedName("simNo")
    private String simNo;
    @SerializedName("versionCode")
    private String versionCode;




}

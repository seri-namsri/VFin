package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.BuildConfig;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.Utility;
import com.mvision.vfin.utility.UtilsEncoding;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class LoginRequestModel {
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceId")
    private String deviceId;
    @SerializedName("platform")
    private String platform;
    @SerializedName("imei")
    private String imei;
    @SerializedName("imei2")
    private String imei2;
    @SerializedName("macAddress")
    private String macAddress;
    @SerializedName("versionCode")
    private String versionCode;
    @SerializedName("deviceModelName")
    private String deviceModelName;
    @SerializedName("registrationId")
    private String registrationId;
    public LoginRequestModel(String userName,String password){
        this.userName = userName;
        this.password = UtilsEncoding.SHA1(password);
        this.deviceId = Utility.getDeviceID(Contextor.getInstance().getContext());
        this.platform = "android";
        this.imei  = Utility.getIMEI1(Contextor.getInstance().getContext())+"";
        this.imei2 = Utility.getIMEI2(Contextor.getInstance().getContext())+"";
        this.macAddress = Utility.getMACAddress("wlan0");
        this.versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        this.deviceModelName = Utility.getDeviceName();
        this.registrationId = PreferencesMange.getInstance().getTokenFCM();
    }
}

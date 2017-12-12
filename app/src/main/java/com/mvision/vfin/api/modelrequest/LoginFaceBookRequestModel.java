package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.BuildConfig;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Utility;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class LoginFaceBookRequestModel {
    @SerializedName("facebookId")
    private String facebookId;
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


    public LoginFaceBookRequestModel(String facebookId) {
        this.facebookId = facebookId;
        this.deviceId = Utility.getDeviceID(Contextor.getInstance().getContext());
        this.platform = "android";
        this.imei  = Utility.getIMEI1(Contextor.getInstance().getContext())+"";
        this.imei2 = Utility.getIMEI2(Contextor.getInstance().getContext())+"";
        this.macAddress = Utility.getMACAddress("wlan0");
        this.versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        this.deviceModelName = Utility.getDeviceName();
    }
}

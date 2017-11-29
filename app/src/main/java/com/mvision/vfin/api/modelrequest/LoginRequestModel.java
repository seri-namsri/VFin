package com.mvision.vfin.api.modelrequest;

import com.mvision.vfin.BuildConfig;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Utility;
import com.mvision.vfin.utility.UtilsEncoding;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class LoginRequestModel {
    private String userName,password, deviceId, platform, imei, imei2, macAddress, versionCode, deviceModelNam;
    public LoginRequestModel(String userName,String password){
        this.userName = userName;
        this.password = UtilsEncoding.SHA1(password);
        this.deviceId = Utility.getDeviceID(Contextor.getInstance().getContext());
        this.platform = "android";
        this.imei  = Utility.getIMEI1(Contextor.getInstance().getContext())+"";
        this.imei2 = Utility.getIMEI2(Contextor.getInstance().getContext())+"";
        this.macAddress = Utility.getMACAddress("wlan0");
        this.versionCode = String.valueOf(BuildConfig.VERSION_CODE);
        this.deviceModelNam = Utility.getDeviceName();
    }
}

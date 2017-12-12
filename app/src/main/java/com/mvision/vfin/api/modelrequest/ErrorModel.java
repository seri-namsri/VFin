package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 11/23/2017 AD.
 */

public class ErrorModel {
    @SerializedName("message")
    public String message = "";
    @SerializedName("errorCode")
    public String errorCode;
    @SerializedName("timestamp")
    public long timestamp;
    @SerializedName("status")
    public long status;
    @SerializedName("error")
    public String error = "";
    @SerializedName("path")
    public String path;
}

package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class VerifyAccountRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("facebookId")
    public String facebookId;
    @SerializedName("mobilePhoneNo")
    public String mobilePhoneNo;
    public VerifyAccountRequest(String mobilePhoneNo,String email,String facebookId){
        this.email = email;
        this.facebookId = facebookId;
        this.mobilePhoneNo = mobilePhoneNo;
    }
}

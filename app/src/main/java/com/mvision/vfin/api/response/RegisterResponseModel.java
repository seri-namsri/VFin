package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class RegisterResponseModel extends BaseRespone{
    @SerializedName("result")
    public Result result;

    public class Result{

    }

}

package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.myaddress.Model.Amphur;
import com.mvision.vfin.component.myaddress.Model.Province;

import java.util.ArrayList;

/**
 * Created by enter_01 on 12/6/2017 AD.
 */

public class AmphurResponseModel extends BaseRespone {
    @SerializedName("result")
    public ArrayList<Amphur> result ;

}

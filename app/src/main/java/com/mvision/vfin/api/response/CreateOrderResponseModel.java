package com.mvision.vfin.api.response;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class CreateOrderResponseModel extends BaseRespone {
    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("shippingPrice")
        public int shippingPrice;


        @SerializedName("systemFeePrice")
        public int systemFeePrice;

    }


}

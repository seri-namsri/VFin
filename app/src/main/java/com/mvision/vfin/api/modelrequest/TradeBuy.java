package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class TradeBuy {
    @SerializedName("memberCode")
    private String memberCode;
    @SerializedName("price")
    public Integer price;
    @SerializedName("productOnShelfCode")
    public String productOnShelfCode;

    public TradeBuy(String memberCode,Integer price,String productOnShelfCode) {
        this.memberCode = memberCode;
        this.price = price;
        this.productOnShelfCode = productOnShelfCode;
    }
}

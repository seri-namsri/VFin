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
    @SerializedName("productOnShelfId")
    public String productOnShelfId;

    public TradeBuy(String memberCode,Integer price,String productOnShelfId) {
        this.memberCode = memberCode;
        this.price = price;
        this.productOnShelfId = productOnShelfId;
    }
}

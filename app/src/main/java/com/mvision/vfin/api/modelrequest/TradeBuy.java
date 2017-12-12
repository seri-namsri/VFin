package com.mvision.vfin.api.modelrequest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class TradeBuy {
    @SerializedName("memberCode")
    private String memberCode;
    @SerializedName("price")
    public int price;
    @SerializedName("productOnShelfId")
    public int productOnShelfId;

    public TradeBuy(String memberCode,int price,int productOnShelfId) {
        this.memberCode = memberCode;
        this.price = price;
        this.productOnShelfId = productOnShelfId;
    }
}

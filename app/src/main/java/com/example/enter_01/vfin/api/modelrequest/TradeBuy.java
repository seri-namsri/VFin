package com.example.enter_01.vfin.api.modelrequest;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class TradeBuy {
    private String memberCode;
    public int price,productOnShelfId;

    public TradeBuy(String memberCode,int price,int productOnShelfId) {
        this.memberCode = memberCode;
        this.price = price;
        this.productOnShelfId = productOnShelfId;
    }
}

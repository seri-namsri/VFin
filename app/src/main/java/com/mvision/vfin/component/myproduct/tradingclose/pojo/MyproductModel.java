package com.mvision.vfin.component.myproduct.tradingclose.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class MyproductModel {
    @SerializedName("name")
    public String name;
    @SerializedName("code")
    public String code;
    @SerializedName("currentPrice")
    public int currentPrice;
    @SerializedName("id")
    public int id;
    @SerializedName("mainImage")
    public String imgUrl;
    @SerializedName("status")
    public String status;
    @SerializedName("createdDate")
    public long createdDate;
    @SerializedName("weight")
    public String weight;
}

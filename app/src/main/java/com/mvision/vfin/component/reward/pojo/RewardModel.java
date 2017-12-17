package com.mvision.vfin.component.reward.pojo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */
@Parcel
public class RewardModel {
    @SerializedName("productCode")
    public String productCode;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("priorityWeight")
    public int priorityWeight;
    @SerializedName("currentPrice")
    public int currentPrice;
    @SerializedName("name")
    public String name;
    @SerializedName("mainImage")
    public String imgUrl;
    @SerializedName("id")
    public String id;
    @SerializedName("languageCode")
    public String languageCode;
    @SerializedName("briefDetails")
    public String briefDetails;
    @SerializedName("fullDetails")
    public String fullDetails;
    @SerializedName("marketPrice")
    public int marketPrice;
    @SerializedName("vatPercent")
    public int vatPercent;
    @SerializedName("priceUnit")
    public String priceUnit;
    @SerializedName("weight")
    public String weight;
    @SerializedName("images")
    public ArrayList<String> images;
}

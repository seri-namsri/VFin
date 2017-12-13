package com.mvision.vfin.component.buysell.allproduct.pojo;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/22/2017 AD.
 */
@Parcel
public class ProductRealTimeModel {
    @SerializedName("ownerCode")
    public String ownerCode;
    @SerializedName("avatarLink")
    public String avatarLink;
    @SerializedName("ownerName")
    public String ownerName;
    @SerializedName("name")
    public String name;
    @SerializedName("mainImage")
    public String mainImage;
    @SerializedName("nextPrice")
    public int nextPrice;
    @SerializedName("id")
    public int id;

    public int getMarketPrice() {
        return marketPrice;
    }

    @SerializedName("marketPrice")
    public int marketPrice;
    @SerializedName("expiredTime")
    public long expiredTime;
    @SerializedName("startTime")
    public long startTime;

    public ArrayList<String> getImages() {
        return images;
    }
    @SerializedName("images")
    public ArrayList<String>images;


    public String getOwnerCode() {
        return ownerCode;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public String getName() {
        return name;
    }

    public String getMainImage() {
        return mainImage;
    }

    public int getNextPrice() {
        return nextPrice;
    }

    public int getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }



   // @SerializedName("integerArrayListId")
    public ArrayList<String>integerArrayListId;

    public ArrayList<String> getIntegerArrayListId() {
        return integerArrayListId;
    }

    public void setIntegerArrayListId(ArrayList<String> integerArrayListId) {
        this.integerArrayListId = integerArrayListId;
    }
}

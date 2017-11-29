package com.mvision.vfin.component.buysell.allproduct.pojo;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/22/2017 AD.
 */

public class ProductRealTimeModel {
    public String ownerCode,avatarLink,ownerName,name,imgUrl;
    public int nextPrice;
    public int id;
    public long expiredTime;

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

    public String getImgUrl() {
        return imgUrl;
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

    public long startTime;

    public ArrayList<String>integerArrayListId;

    public ArrayList<String> getIntegerArrayListId() {
        return integerArrayListId;
    }

    public void setIntegerArrayListId(ArrayList<String> integerArrayListId) {
        this.integerArrayListId = integerArrayListId;
    }
}

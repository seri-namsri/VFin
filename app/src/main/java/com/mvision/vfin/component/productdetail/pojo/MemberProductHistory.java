package com.mvision.vfin.component.productdetail.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by enter_01 on 12/13/2017 AD.
 */

public class MemberProductHistory {
    @SerializedName("avatarLink")
    public String avatarLink;

    public String getAvatarLink() {
        return avatarLink;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public String getName() {
        return name;
    }

    @SerializedName("buyPrice")
    public int buyPrice;
    @SerializedName("buyTime")
    public long buyTime;
    @SerializedName("name")
    public String name;
}

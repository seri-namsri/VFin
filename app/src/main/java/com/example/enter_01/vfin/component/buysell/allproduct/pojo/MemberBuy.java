package com.example.enter_01.vfin.component.buysell.allproduct.pojo;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by enter_01 on 11/10/2017 AD.
 */

@Parcel
public class MemberBuy {



    public long date_buy;

    public long getDate_buy() {
        return date_buy;
    }

    public int getPrice() {
        return price;
    }

    public String getMember_id() {
        return member_id;
    }

    public int price;
    public String member_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String username ;

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String imageProfile;
}

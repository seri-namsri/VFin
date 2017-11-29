package com.mvision.vfin.component.reward.pojo;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/14/2017 AD.
 */
@Parcel
public class RewardModel {
    public String getName() {
        return name;
    }

    public String name;

    public String getCat_id() {
        return cat_id;
    }

    public String getDetail() {
        return detail;
    }

    public String getFull_detail() {
        return full_detail;
    }

    public String getReward_id() {
        return reward_id;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<String> getImage_product() {
        return image_product;
    }

    public String cat_id;

    public String getCat_name() {
        return cat_name;
    }

    public String cat_name;
    public String detail;
    public String full_detail;
    public String reward_id ;
    public int price ;
    public ArrayList<String>image_product;
}

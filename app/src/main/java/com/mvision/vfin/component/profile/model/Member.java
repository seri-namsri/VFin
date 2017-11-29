package com.mvision.vfin.component.profile.model;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Member {



    public String getEmail() {
        return email;
    }

    public String getImage_profile() {
        return image_profile;
    }

    public String getName() {
        return name;
    }

    private String email;
    private String image_profile;
    private String name;
    private String tel;
    private String birdday;
    private String gender;
    private String identification;

    public String getMemberCode() {
        return memberCode;
    }

    private String memberCode;
    private int energy;

    public int getEnergy() {
        return energy;
    }

    public int getCoin() {
        return coin;
    }

    private int coin;

    public String getMember_id() {
        return member_id;
    }

    private String member_id;

    public ArrayList<Address> getAddress() {
        return address;
    }

    private ArrayList<Address> address;
    public String getTel() {
        return tel;
    }

    public String getBirdday() {
        return birdday;
    }

    public String getGender() {
        return gender;
    }

    public String getIdentification() {
        return identification;
    }

    public ArrayList<String> getDevice_token() {
        return device_token;
    }

    public ArrayList<String>device_token ;
}

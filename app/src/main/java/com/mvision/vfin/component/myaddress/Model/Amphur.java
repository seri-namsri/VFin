package com.mvision.vfin.component.myaddress.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 12/6/2017 AD.
 */
@Parcel
public class Amphur{
    @SerializedName("id")
    public int id;
    @SerializedName("amphurCode")
    public String amphurCode;
    @SerializedName("amphurName")
    public String amphurName;
    @SerializedName("postCode")
    public String postCode;

}
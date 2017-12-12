package com.mvision.vfin.component.myaddress.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 12/6/2017 AD.
 */

@Parcel
public class Province {
    @SerializedName("id")
    public int id;
    @SerializedName("provinceCode")
    public String provinceCode;
    @SerializedName("provinceName")
    public String provinceName;
    @SerializedName("prefix")
    public String prefix;


}

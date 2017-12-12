package com.mvision.vfin.component.profile.model;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.addeditdress.model.AddressModel;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/28/2017 AD.
 */
@Parcel
public class MemberResponseModel extends BaseRespone{
    @SerializedName("result")
    public Result result ;
    @Parcel
    public static  class Result {

        @SerializedName("avatarLink")
        public String avatarLink;
        @SerializedName("firstName")
        public String firstName;
        @SerializedName("lastName")
        public String lastName;
        @SerializedName("email")
        public String email;
        @SerializedName("gender")
        public String gender;
        @SerializedName("personalId")
        public String personalId;
        @SerializedName("dateOfBirth")
        public String dateOfBirth;
        @SerializedName("mobilePhoneNo")
        public String mobilePhoneNo;
        @SerializedName("avatarFilePath")
        public String avatarFilePath;
        @SerializedName("memberLevelLimit")
        public MemberLevelLimit memberLevelLimit;
        @SerializedName("memberLevel")
        public int memberLevel;

        @SerializedName("address")
        public AddressModel address;


    }
    @Parcel
    public static  class MemberLevelLimit{
        @SerializedName("memberLevel")
       public int levelUsagePoint;
        @SerializedName("levelUsagePointStep")
       public int levelUsagePointStep;
        @SerializedName("limitPerDay")
       public int limitPerDay;
        @SerializedName("limitRedeem")
       public int limitRedeem;
        @SerializedName("maxPointForNextLevel")
       public int maxPointForNextLevel;
        @SerializedName("usagePointForRedeem")
       public int usagePointForRedeem;
    }
}

package com.mvision.vfin.component.profile.model;

import com.google.gson.annotations.SerializedName;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseRespone;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.utility.Contextor;

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

        public String getLastName() {
            return (lastName==null) ? "" : lastName;
        }

        @SerializedName("lastName")
        public String lastName;
        @SerializedName("email")
        public String email;

        public String getGender() {
            try {
                if (gender.equals("male"))
                    return Contextor.getInstance().getContext().getString(R.string.layoutChangeGenderMen);
                else if (gender.equals("female"))
                    return Contextor.getInstance().getContext().getString(R.string.layoutChangeGenderFemale);
            }catch (NullPointerException e){
                return "";
            }
            return "";
        }

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

package com.mvision.vfin.component.profile.model;

import com.mvision.vfin.base.BaseRespone;

import org.parceler.Parcel;

/**
 * Created by enter_01 on 11/28/2017 AD.
 */
@Parcel
public class MemberResponseModel extends BaseRespone{

    public Result result ;
    @Parcel
    public static  class Result {
        public String avatarLink,firstName,lastName,email,sex,personalId,dateOfBirth,mobilePhoneNo;
        public MemberLevelLimit memberLevelLimit;
        public int memberLevel;
    }
    @Parcel
    public static  class MemberLevelLimit{
       public int levelUsagePoint,levelUsagePointStep,limitPerDay,limitRedeem,
               maxPointForNextLevel,usagePointForRedeem;
    }
}

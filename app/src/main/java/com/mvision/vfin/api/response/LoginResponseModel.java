package com.mvision.vfin.api.response;

import com.mvision.vfin.base.BaseRespone;

/**
 * Created by enter_01 on 11/20/2017 AD.
 */

public class LoginResponseModel extends BaseRespone {

    public Result result;

    public class Result {

        public String tokenSession;
        public Member member;
    }

    public class Member{
        public String avatarLink,firstName,lastName,email,sex,mobilePhoneNo,dateOfBirth,memberCode;
    }

}
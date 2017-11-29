package com.mvision.vfin.api.request;

import com.mvision.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.mvision.vfin.api.modelrequest.LoginRequestModel;
import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Member {

    @POST("memberservice/v2/member/login")
    Observable<LoginResponseModel> loginVfin(@Body LoginRequestModel body);

    @POST("memberservice/v2/member/connectWithFacebook")
    Observable<LoginResponseModel> loginFaceBookVfin(@Body LoginFaceBookRequestModel body);

    @GET("memberservice/v2/member/getAddress")
    Observable<MyAddressResponseModel> myAddress(@Query("memberCode") String memberCode );

    @GET("memberservice/v2/member/getMember")
    Observable<MemberResponseModel> getMember(@Query("memberCode") String memberCode );

}
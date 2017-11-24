package com.example.enter_01.vfin.api.request;

import com.example.enter_01.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.example.enter_01.vfin.api.modelrequest.LoginRequestModel;
import com.example.enter_01.vfin.api.modelrequest.MyAddressRequestModel;
import com.example.enter_01.vfin.api.modelrequest.RequestFcm;
import com.example.enter_01.vfin.api.response.LoginResponseModel;
import com.example.enter_01.vfin.api.response.ModelFcm;
import com.example.enter_01.vfin.api.response.MyAddressResponseModel;

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

}
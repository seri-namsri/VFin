package com.mvision.vfin.api.request;

import com.mvision.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.mvision.vfin.api.modelrequest.LoginRequestModel;
import com.mvision.vfin.api.modelrequest.UpdateProfileRequestModel;
import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
    @FormUrlEncoded
    @POST("memberservice/v2/member/updateMemberProfile")
    Observable<UpdateProfileResponseModel> updateMember(@Field("member") String member, @Field("type") String type);

    @Multipart
    @POST("memberservice/v2/member/updateMemberProfile")
    Observable<UpdateProfileResponseModel> uploadImage(@Part("member") RequestBody member, @Part
            ("type") RequestBody type, @Part MultipartBody.Part image);
}
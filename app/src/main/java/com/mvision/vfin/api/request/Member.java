package com.mvision.vfin.api.request;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.ForgotPasswordRequest;
import com.mvision.vfin.api.modelrequest.LoginFaceBookRequestModel;
import com.mvision.vfin.api.modelrequest.LoginRequestModel;
import com.mvision.vfin.api.modelrequest.RegisterRequest;
import com.mvision.vfin.api.modelrequest.VerifyAccountRequest;
import com.mvision.vfin.api.modelrequest.VerifyOtpRequest;
import com.mvision.vfin.api.response.AmphurResponseModel;
import com.mvision.vfin.api.response.ForgotPasswordModelRespone;
import com.mvision.vfin.api.response.LoginResponseModel;
import com.mvision.vfin.api.response.MyAddressResponseModel;
import com.mvision.vfin.api.response.ProvinceResponseModel;
import com.mvision.vfin.api.response.RegisterResponseModel;
import com.mvision.vfin.api.response.SaveAddressResponse;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.api.response.VerifyOtpResponseModel;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.profile.model.MemberResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Hytexts_Android on 13/9/2559.
 */

public interface Member {
    @POST
    Observable<LoginResponseModel> loginVfin(@Url String url,@Body LoginRequestModel body);

    @POST
    Observable<LoginResponseModel> loginFaceBookVfin(@Url String url,@Body LoginFaceBookRequestModel body);

    @POST
    Observable<SaveAddressResponse> saveAddress(@Url String url,@Query("memberCode")String memberCode,@Body
            AddressModel body);

    @GET
    Observable<MyAddressResponseModel> myAddress(@Url String url,@Query("memberCode") String memberCode );

    @POST
    Observable<ErrorModel> updateAddressIsPrimary(@Url String url,@Query("memberCode") String memberCode , @Query
            ("addressId")long addressId);

    @GET
    Observable<MemberResponseModel> getMember(@Url String url,@Query("memberCode") String memberCode );

    @GET("memberservice/v2/member/getProvince")
    Observable<ProvinceResponseModel> getProvince();

    @GET("memberservice/v2/member/getAmphur")
    Observable<AmphurResponseModel> getAmphur(@Query("provinceId")int provinceId);

    @FormUrlEncoded
    @POST
    Observable<UpdateProfileResponseModel> updateMember(@Url String url,@Field("member") String member, @Field("type") String type);

    @Multipart
    @POST
    Observable<UpdateProfileResponseModel> uploadImage(@Url String url,@Part("member") RequestBody member, @Part
            ("type") RequestBody type, @Part MultipartBody.Part image);

    @POST
    Observable<ForgotPasswordModelRespone> forgotPassword(@Url String url,@Body ForgotPasswordRequest forgotPasswordRequest );

    @POST
    Observable<VerifyOtpResponseModel> verifyOtp(@Url String url,@Body VerifyOtpRequest verifyOtpRequest );

    @POST
    Observable<VerifyOtpResponseModel> updateNewPassword(@Url String url,@Body VerifyOtpRequest verifyOtpRequest );

    @POST
    Observable<ForgotPasswordModelRespone> verifyAccount(@Url String url,@Body VerifyAccountRequest verifyAccountRequest );

    @POST
    Observable<RegisterResponseModel> register(@Url String url,@Body RegisterRequest registerRequest );
}
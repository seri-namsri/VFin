package com.mvision.vfin.component.profiledetail;

import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.RetrofitUtility;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/30/2017 AD.
 */

public class ProfileDetailManage {
    private static ProfileDetailManage instance = null;

    public static ProfileDetailManage getInstance() {
        if (instance == null)
            instance = new ProfileDetailManage();
        return instance;
    }


    public void upLoadImage(final byte[] imageBytes, final Query.CallBackData callBackData) {

        RequestBody image = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes);
        RequestBody member = RequestBody.create(MediaType.parse("text/plain"), new Gson().toJson(new MemberUpdate()));
        RequestBody type = RequestBody.create(MediaType.parse("text/plain"), "image");
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "image.jpg", image);
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Member.class).uploadImage(GetKey.getInstance().apiUpdateMemberProfile(GetKey
                .getInstance()
                .getSignatures()),member, type, body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateProfileResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                upLoadImage(imageBytes,callBackData);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });

                    }

                    @Override
                    public void onNext(UpdateProfileResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });
    }


}

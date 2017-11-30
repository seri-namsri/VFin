package com.mvision.vfin.component.editprofile;

import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.modelrequest.UpdateProfileRequestModel;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.RetrofitUtility;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public class EditProfileManage {

    private static EditProfileManage instance = null;

    public static EditProfileManage getInstance() {
        if (instance == null)
            instance = new EditProfileManage();
        return instance;
    }


    public void updateProfile(String type, final MemberUpdate memberUpdate,
                              final Query.CallBackData callBackData){
        RetrofitUtility.getInstance().getRetrofit().create(com.mvision.vfin.api.request.Member.class)
                .updateMember(new Gson().toJson(memberUpdate),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdateProfileResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                      //  e.printStackTrace();
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();

                            try {
                                ErrorModel errorModel = new Gson().fromJson(body
                                        .string(), ErrorModel.class);

                                callBackData.onFail(errorModel.message);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onNext(UpdateProfileResponseModel s) {
                        callBackData.onSuccess(memberUpdate);
                    }
                });
    }


}

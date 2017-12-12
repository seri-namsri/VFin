package com.mvision.vfin.component.editprofile;

import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.api.response.UpdateProfileResponseModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.RetrofitUtility;
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


    public void updateProfile(final String type, final MemberUpdate memberUpdate,
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
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                updateProfile(type,memberUpdate,callBackData);
                            }
                        });
                    }

                    @Override
                    public void onNext(UpdateProfileResponseModel s) {
                        callBackData.onSuccess(memberUpdate);
                    }
                });
    }


}

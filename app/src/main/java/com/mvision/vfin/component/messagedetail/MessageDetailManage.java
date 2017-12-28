package com.mvision.vfin.component.messagedetail;

import com.mvision.vfin.api.request.Message;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 12/26/2017 AD.
 */

public class MessageDetailManage {

    private static MessageDetailManage instance = null;

    public static MessageDetailManage getInstance() {
        if (instance == null)
            instance = new MessageDetailManage();
        return instance;
    }


    public void deleteMessage(final String code, final Query.CallBackData callBackData){
        RetrofitUtility.getInstance().getRetrofit().create(Message.class).deleteMessage(GetKey.getInstance()
                .apiMessageDelete(GetKey.getInstance().getSignatures())+PreferencesMange
                .getInstance().getMemberID()+"/"+code )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                deleteMessage(code,callBackData);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(Object s) {
                        callBackData.onSuccess(s);
                    }
                });
    }

}

package com.mvision.vfin.component.profile;

import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileManager {

    private static ProfileManager instance = null;
    private static FirebaseFirestore db;

    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
    }

    public void getMember(String memberId, final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocument(db.document("member/" + memberId), new Member(), callBackData);
    }

    public void getMemberNorealTime(String memberId, final Query.CallBackData callBackData) {
        db = FirebaseFirestore.getInstance();
        try {
            Query.getInstance().readDataDocumentNorealTime(db.document("member/" + memberId), new Member(),
                    callBackData);
        } catch (Exception e) {
        }

    }

    public void getMemberApi(final Query.CallBackData callBackData) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Member.class).getMember(PreferencesMange
                .getInstance().getMemberID().trim()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MemberResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            try {
                                ErrorModel errorModel = new Gson().fromJson(body
                                        .string(), ErrorModel.class);
                                callBackData.onFail(errorModel.message);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onNext(MemberResponseModel s) {
                        Log.e("MyAddressResponseModel", new Gson().toJson(s));
                        callBackData.onSuccess(s);

                    }

                });
    }


}

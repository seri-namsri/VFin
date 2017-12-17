package com.mvision.vfin.component.profile;
import com.google.gson.Gson;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileManager {

    private static ProfileManager instance = null;

    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
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
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {

                                getMemberApi(callBackData);
                            }
                        });

                    }

                    @Override
                    public void onNext(MemberResponseModel s) {
                        callBackData.onSuccess(s);

                    }

                });
    }
}

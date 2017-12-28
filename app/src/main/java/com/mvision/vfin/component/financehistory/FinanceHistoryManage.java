package com.mvision.vfin.component.financehistory;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mvision.vfin.api.request.Wallet;
import com.mvision.vfin.api.response.WalletTransectionResponseModel;
import com.mvision.vfin.component.configkey.GetKey;
import com.mvision.vfin.component.financehistory.pojo.FinanceHistoryModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/17/2017 AD.
 */

public class FinanceHistoryManage {
    private static FinanceHistoryManage instance = null;

    public static FinanceHistoryManage getInstance() {
        if (instance == null)
            instance = new FinanceHistoryManage();
        return instance;
    }


    public void getFinanceHistory(final Query.CallBackData callBackData, final int pageSize, final int startPosition) {
        RetrofitUtility.getInstance().getRetrofit().create(Wallet.class)
                .getWalletTransaction(GetKey.getInstance()
                        .apiGetWalletTransaction(GetKey
                                .getInstance()
                                .getSignatures()),PreferencesMange
                        .getInstance().getMemberID(), pageSize, startPosition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WalletTransectionResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ErrorMange.getInstance().setError(e, new ErrorMange.CallBackError() {
                            @Override
                            public void reloadConnect() {
                                getFinanceHistory(callBackData, pageSize, startPosition);
                            }

                            @Override
                            public void clickCancel() {

                            }
                        });
                    }

                    @Override
                    public void onNext(WalletTransectionResponseModel s) {
                        callBackData.onSuccess(s);
                    }
                });
    }
    private ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Gson gson = new Gson();
            ModelCoinAndBit modelCoinAndBit = gson.fromJson(gson.toJson(dataSnapshot.getValue
                    ()), ModelCoinAndBit.class);
            callBackData.onSuccess(modelCoinAndBit);
            return;
        }

        @Override
        public void onCancelled(DatabaseError error) {
        }
    };

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private     DatabaseReference myRef ;
    private  Query.CallBackData callBackData ;
    public void getCoin(final Query.CallBackData callBackData) {
        this.callBackData = callBackData;
         myRef = database.getReference("walletAndAbility/" + PreferencesMange
                .getInstance().getMemberID());
        myRef.addValueEventListener(valueEventListener);

    }


    public void stopFileBaseRealtime() {
        myRef.removeEventListener(valueEventListener);
    }


}

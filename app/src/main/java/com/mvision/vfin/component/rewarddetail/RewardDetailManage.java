package com.mvision.vfin.component.rewarddetail;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mvision.vfin.api.modelrequest.TradeBuy;
import com.mvision.vfin.api.response.RewardDetailResponseModel;
import com.mvision.vfin.api.response.TradeBuyResponseModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.reward.pojo.RewardModel;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.RetrofitUtility;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enter_01 on 11/15/2017 AD.
 */

public class RewardDetailManage {

    private static RewardDetailManage instance = null;
    private static CallBackData callBackData ;
    public static RewardDetailManage getInstance(CallBackData callBackDataReward) {
        if (instance == null)
            instance = new RewardDetailManage();
        callBackData = callBackDataReward;
        return instance;
    }
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRefWallet ;
    private ValueEventListener valueEventListenerWallet ;

    public void getCoin(){
        myRefWallet = database.getReference("walletAndAbility/" + PreferencesMange
                .getInstance().getMemberID());
        valueEventListenerWallet = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Gson gson = new Gson();
                ModelCoinAndBit modelCoinAndBit = gson.fromJson(gson.toJson(dataSnapshot.getValue
                        ()),ModelCoinAndBit.class);
                callBackData.getCoin(modelCoinAndBit);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
        myRefWallet.addValueEventListener(valueEventListenerWallet);
    }

    public void stopRealTime(){
        myRefWallet.removeEventListener(valueEventListenerWallet);
    }


    public interface CallBackData {
        void getCoin(ModelCoinAndBit modelCoinAndBit);
        void trade(TradeBuyResponseModel tradeBuyResponseModel);
        void rewardDetail(RewardDetailResponseModel rewardDetailResponseModel);
    }



    public void tradeBuyProduct(final RewardModel rewardModel) {
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Product.class)
                .tradeBuy(new TradeBuy(PreferencesMange.getInstance().getMemberID(),null,
                        rewardModel.id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TradeBuyResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TradeBuyResponseModel s) {
                        callBackData.trade(s);

                    }

                });
    }

    public void getRewardDetail(String rewardId){
        RetrofitUtility.getInstance()
                .getRetrofit()
                .create(com.mvision.vfin.api.request.Product.class)
                .getRewardDetail(rewardId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RewardDetailResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RewardDetailResponseModel s) {
                        callBackData.rewardDetail(s);

                    }

                });
    }
}

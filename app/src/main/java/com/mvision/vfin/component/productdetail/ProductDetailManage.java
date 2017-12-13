package com.mvision.vfin.component.productdetail;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.productdetail.pojo.MemberProductHistory;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.firebase.Firestore.Update;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailManage {
    private static ProductDetailManage instance = null;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static CallBackMemberData callBackMemberData ;

    private DatabaseReference myRefProduct ;
    private DatabaseReference myRefProductHistory ;
    private DatabaseReference myRefWallet ;

    private ValueEventListener valueEventListenerWallet ;
    private ValueEventListener valueEventListenerProduct ;
    private ChildEventListener valueEventListenerProductHistory ;

    public static ProductDetailManage getInstance(CallBackMemberData callBackMember) {
        callBackMemberData = callBackMember;
        if (instance == null)
            instance = new ProductDetailManage();

        return instance;
    }



    public void getProductDetailRealTime(String productId){
        myRefProduct = database.getReference("product/" + productId);
        valueEventListenerProduct = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ProductRealTimeModel productRealTimeModel = dataSnapshot.getValue
                        (ProductRealTimeModel.class);
                callBackMemberData.memberData(productRealTimeModel);
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //   Log.w("getAllProductFormRealtime", "Failed to read value.", error.toException());
            }
        };
        myRefProduct.addValueEventListener(valueEventListenerProduct);
    }


    public void getCoin(){
        myRefWallet = database.getReference("walletAndAbility/" + PreferencesMange
                .getInstance().getMemberID());
        valueEventListenerWallet = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Gson gson = new Gson();
                ModelCoinAndBit modelCoinAndBit = gson.fromJson(gson.toJson(dataSnapshot.getValue
                        ()),ModelCoinAndBit.class);
                callBackMemberData.getCoin(modelCoinAndBit);
                return;
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };
        myRefWallet.addValueEventListener(valueEventListenerWallet);
    }


    public void stopRealTime(){
        myRefWallet.removeEventListener(valueEventListenerWallet);
        myRefProduct.removeEventListener(valueEventListenerProduct);
        myRefProductHistory.removeEventListener(valueEventListenerProductHistory);
    }

    public interface CallBackMemberData {
        void memberData(ProductRealTimeModel productRealTimeModel);
        void getCoin(ModelCoinAndBit modelCoinAndBit);
        void getMemberProductHistory(ArrayList<MemberProductHistory> memberProductHistories);
    }

    public void getMemberProductHistory(String productId){
        final ArrayList<MemberProductHistory>memberProductHistories = new ArrayList<>();
        myRefProductHistory = database.getReference("memberProductHistory/" + productId)
                .limitToFirst(5).getRef();
        valueEventListenerProductHistory = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MemberProductHistory memberProductHistory = dataSnapshot.getValue
                        (MemberProductHistory.class);
                memberProductHistories.add(memberProductHistory);
                if (memberProductHistories.size() >=1)
                    callBackMemberData.getMemberProductHistory(memberProductHistories);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRefProductHistory.addChildEventListener(valueEventListenerProductHistory);
    }

}


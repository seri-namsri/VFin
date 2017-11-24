package com.example.enter_01.vfin.component.productdetail;

import com.example.enter_01.vfin.component.buysell.allproduct.AllProductManage;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.message.pojo.MessageData;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.firebase.Firestore.Update;
import com.example.enter_01.vfin.utility.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailManage {
    private static ProductDetailManage instance = null;
    private static FirebaseFirestore db;

    public static ProductDetailManage getInstance() {
        if (instance == null)
            instance = new ProductDetailManage();

        return instance;
    }



    public void upDateProduct(final Query.CallBackData callBackData, ProductModel productModel) {
        db = FirebaseFirestore.getInstance();
        Update.getInstance().updateDocument(db.document("product/" + productModel.getProduct_id()),
                productModel, new Update.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {
                        callBackData.onSuccess(null);
                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                        callBackData.onSuccessAll(null);

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });
    }

    public void buyProductWithAPi(final Query.CallBackData callBackData, ProductModel productModel){

    }

    public void upDateReturnCoinMemberOwner(final Query.CallBackData callBackData, String memberId
            ,int coin) {
        db = FirebaseFirestore.getInstance();
        Update.getInstance().updateDocument(db.document("member/" + memberId).update("coin",
                coin),
                new Update.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {

                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });
    }




    public void upDateEnergyCoinMemberMe(final Query.CallBackData callBackData,String memberId, int
            energy,int coin) {
        db = FirebaseFirestore.getInstance();
        Update.getInstance().updateDocument(db.document("member/" + memberId).update("coin",
                coin,"energy",energy),
                 new Update.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {

                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });
    }



    public void getBuyUser(final Query.CallBackData callBackData, String idProduct) {
        db = FirebaseFirestore.getInstance();
        Query.getInstance().readDataDocument(db.document("product/" + idProduct)
                , new ProductModel(), new Query.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {
                        getDataMember((ProductModel) t, new CallBackMemberData() {
                            @Override
                            public void memberData(ProductModel productModel) {
                                callBackData.onSuccess(productModel);
                            }
                        });
                        //  callBackData.onSuccess(t);
                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {


                        //                  callBackData.onSuccessAll(memberBuys);

                    }

                    @Override
                    public void onFail(String error) {
                        callBackData.onFail(error);
                    }
                });
    }

    public void getDataMember(final ProductModel productModel, final CallBackMemberData callBackMemberData) {
        db = FirebaseFirestore.getInstance();
        final int[] i = {0};
        if (productModel.getMember_buy() != null){
            for (final MemberBuy memberBuy : productModel.getMember_buy()) {
                Query.getInstance().readDataDocument(db.document("member/" + memberBuy.getMember_id()), new
                        Member(), new
                        Query.CallBackData() {
                            @Override
                            public <T> void onSuccess(T t) {
                                Member member = (Member) t;
                                i[0]++;
                                try {
                                    memberBuy.setUsername(member.getName());
                                    memberBuy.setImageProfile(member.getImage_profile());

                                } catch (Exception e) {
                                    memberBuy.setUsername("Empty");
                                    memberBuy.setImageProfile("Empty");
                                }
                                if (i[0] == productModel.getMember_buy().size()) {
                                    callBackMemberData.memberData(productModel);
                                }
                                //i[0]++ ;
                            }

                            @Override
                            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                            }

                            @Override
                            public void onFail(String error) {

                            }
                        });
            }
        }else {
            callBackMemberData.memberData(productModel);
        }

    }

    private interface CallBackMemberData {
        void memberData(ProductModel productModel);
    }

}


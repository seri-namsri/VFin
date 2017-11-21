package com.example.enter_01.vfin.component.buysell.allproduct;

import android.os.CountDownTimer;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.productdetail.ProductDetailManage;
import com.example.enter_01.vfin.component.profile.ProfileManager;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class AllProductPresenter extends Presenter<AllProductContract.View> implements AllProductContract.Presenter {

    private AllProductContract.View view;

    public AllProductPresenter(AllProductContract.View view) {
        this.view = view;
    }

    @Override
    public void getProductAll() {
        view.showLoading();
        AllProductManage.getInstance().getAllProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                view.hideLoading();
                ArrayList<ProductModel> arrayList = (ArrayList<ProductModel>) tArrayList;

                view.setUpViewAllProduct(arrayList);
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void buyProduct(final ProductModel productModel, final int price, String memberIdOwner) {
        Long tsLong = System.currentTimeMillis() / 1000;
        MemberBuy memberBuy = new MemberBuy();
        memberBuy.member_id = PreferencesMange.getInstance().getMemberID();
        memberBuy.date_buy = tsLong;
        memberBuy.price = price;
        try {
            productModel.member_buy.add(memberBuy);
        } catch (NullPointerException e) {
            e.printStackTrace();
            productModel.member_buy = new ArrayList<>();
            productModel.member_buy.add(memberBuy);
        }
        final String finalMemberIdOwner = memberIdOwner;
        ProductDetailManage.getInstance().upDateProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {


            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                getMemberUpdate(price);
                getMemberIdOwner(finalMemberIdOwner, price,productModel.getName(),productModel
                        .getImage_product().get(0));
            }

            @Override
            public void onFail(String error) {

            }
        }, productModel);

    }


    private void getMemberUpdate(final int price) {
        ProfileManager.getInstance().getMemberNorealTime(PreferencesMange.getInstance().getMemberID(), new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                Member member = (Member) t;

                ProductDetailManage.getInstance().upDateEnergyCoinMemberMe(new Query.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {

                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                },PreferencesMange.getInstance().getMemberID(), member.getEnergy() - 1
                        , member.getCoin() - price);


            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });

    }

    private void getMemberIdOwner(final String memberIDOwner, final int price, final String
            productName,final  String imageProduct) {

        ProfileManager.getInstance().getMemberNorealTime(memberIDOwner, new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                Member member = (Member) t;
                AllProductManage.getInstance().setNotiFcm(member.getDevice_token(),productName,
                        imageProduct,price);
                ProductDetailManage.getInstance().upDateReturnCoinMemberOwner(new Query.CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {

                    }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                }, memberIDOwner, member.getCoin() + price);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}

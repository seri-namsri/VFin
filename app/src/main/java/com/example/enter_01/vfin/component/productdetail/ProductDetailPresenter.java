package com.example.enter_01.vfin.component.productdetail;

import android.os.Bundle;
import android.os.Parcel;

import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.buysell.allproduct.AllProductFragment;
import com.example.enter_01.vfin.component.buysell.allproduct.AllProductManage;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.profile.ProfileManager;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailPresenter extends Presenter<ProductDetailContract.View> implements
        ProductDetailContract.Presenter{

    private ProductDetailContract.View view;
    private ProductModel productModel ;

    public ProductDetailPresenter(ProductDetailContract.View view){
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        productModel = Parcels.unwrap(extras.getParcelable(AllProductFragment.clickProductDetail));
    }

    @Override
    public void getProductDetail() {
        ProductDetailManage.getInstance().getBuyUser(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                view.setUpViewProductDetail((ProductModel) t);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                productModel.setBuy_member((ArrayList<Member>) tArrayList);

                view.setUpViewProductDetail(productModel);
            }

            @Override
            public void onFail(String error) {

            }
        },productModel.getProduct_id());
    }

    @Override
    public void getProductDetailAlert() {

        view.setUpViewProductDetailDetail(productModel.getFull_detail());
    }

    @Override
    public void buyProduct(final int price, final String memberIDOwner, final String productName,
                           final String imageProduct) {
        Long tsLong = System.currentTimeMillis()/1000;
        MemberBuy memberBuy = new MemberBuy();
        memberBuy.member_id = PreferencesMange.getInstance().getMemberID();
        memberBuy.date_buy = tsLong;
        memberBuy.price = price;
        try {

            productModel.getMember_buy().add(memberBuy);
        }catch (NullPointerException e){
            productModel.member_buy = new ArrayList<>();
            productModel.getMember_buy().add(memberBuy);
        }
        ProductDetailManage.getInstance().upDateProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
                getMemberUpdate(price);
                getMemberIdOwner(memberIDOwner,price,productName,imageProduct);
            }

            @Override
            public void onFail(String error) {

            }
        },productModel);



    }



    private void getMemberUpdate(final int price){
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
                },PreferencesMange.getInstance().getMemberID(),member.getEnergy()-1
                        ,member.getCoin()-price);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    private void getMemberIdOwner(final String memberIDOwner, final int price,final String productName,
                                  final  String imageProduct){

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
                },memberIDOwner,   member.getCoin()+price);
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

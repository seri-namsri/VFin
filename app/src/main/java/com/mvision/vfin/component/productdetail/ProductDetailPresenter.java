package com.mvision.vfin.component.productdetail;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.mvision.vfin.api.response.TimeResponseModel;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.buysell.allproduct.AllProductFragment;
import com.mvision.vfin.component.buysell.allproduct.AllProductManage;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.productdetail.pojo.MemberProductHistory;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailPresenter extends Presenter<ProductDetailContract.View> implements
        ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private ProductRealTimeModel productModel;

    public ProductDetailPresenter(ProductDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        productModel = Parcels.unwrap(extras.getParcelable(AllProductFragment.clickProductDetail));
    }

    @Override
    public void getProductDetail() {
        ProductDetailManage.getInstance(callBackMemberData).getProductDetailRealTime
                (productModel.id + "");
    }

    @Override
    public void getMemberProductHistory() {
        ProductDetailManage.getInstance(callBackMemberData).getMemberProductHistory(productModel.id+"");
    }

    @Override
    public void getProductDetailAlert() {

        //   view.setUpViewProductDetailDetail(productModel.getFull_detail());
    }

    @Override
    public void getCoin() {
        ProductDetailManage.getInstance(callBackMemberData).getCoin();
    }

    @Override
    public void stopTime() {
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    @Override
    public void stopRealTime() {
        ProductDetailManage.getInstance(callBackMemberData).stopRealTime();
    }

    private ProductDetailManage.CallBackMemberData callBackMemberData = new ProductDetailManage.CallBackMemberData() {
        @Override
        public void memberData(ProductRealTimeModel productRealTimeModel) {
            if (productRealTimeModel != null) {
                productModel = productRealTimeModel;
                view.setUpViewProductDetail(productRealTimeModel);
                checkDataStatusTrade(productRealTimeModel);
            }
        }

        @Override
        public void getCoin(ModelCoinAndBit modelCoinAndBit) {
            view.setUpViewCoin(modelCoinAndBit);
        }

        @Override
        public void getMemberProductHistory(ArrayList<MemberProductHistory> memberProductHistories) {
            view.setUpViewMemberProductHistory(memberProductHistories);
        }
    };

    private void checkDataStatusTrade(final ProductRealTimeModel productRealTimeModel) {

        AllProductManage.getInstance().getTime(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                TimeResponseModel timeResponseModel = (TimeResponseModel) t;
                view.buttonLoadingHide();
                if (productRealTimeModel.getExpiredTime() < timeResponseModel.result) {
                    if (checkItemOwner(productRealTimeModel.getOwnerCode()))
                        view.setTimeOutMyItem();
                    else
                        view.setTimeOut();
                } else if (checkItemOwner(productRealTimeModel.ownerCode)) {
                    timeCountDown(productRealTimeModel.expiredTime, timeResponseModel.result);
                    view.setSell(productRealTimeModel);
                } else {
                    timeCountDown(productRealTimeModel.expiredTime, timeResponseModel.result);
                    view.setBuy(productRealTimeModel);
                }
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {
            }

            @Override
            public void onFail(String error) {
            }

        });
    }

    private CountDownTimer countDownTimer;

    private void timeCountDown(long timeExpired, long courent) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        final long time1 = timeExpired - courent;

        final int millis = (int) time1;
        df.setTimeZone(tz);
        String time = df.format(new Date(millis));
        view.startTime(time);
        countDownTimer = new CountDownTimer(time1, 1000) {

            public void onTick(long millisUntilFinished) {
                try {
                    int millis = (int) (millisUntilFinished);
                    df.setTimeZone(tz);
                    String time = df.format(new Date(millis));
                    view.startTime(time);
                } catch (Exception e) {
                }
            }

            public void onFinish() {
                if (checkItemOwner(productModel.getOwnerCode()))
                    view.setTimeOutMyItem();
                else
                    view.setTimeOut();
            }
        }.start();
    }


    private boolean checkItemOwner(String memberOwner) {
        if (PreferencesMange.getInstance().getMemberID().equals(memberOwner)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void buyProduct() {
        view.buttonLoadingShow();
        AllProductManage.getInstance().tradeBuyProduct(new Query.CallBackDataTrade() {
            @Override
            public <T> void onSuccess(T t) {
              //  view.buttonLoadingHide();
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }

            @Override
            public void onItemFail(int position) {

            }
        }, productModel);

    }

}

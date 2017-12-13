package com.mvision.vfin.component.productdetail;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.productdetail.pojo.MemberProductHistory;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProductDetailContract {

    interface View extends BaseView {

        void setUpViewProductDetail(ProductRealTimeModel productRealTimeModel);
        void setUpViewMemberProductHistory(ArrayList<MemberProductHistory>memberProductHistory);
        void setUpViewProductDetailDetail(String detail);
        void setUpViewCoin(ModelCoinAndBit modelCoinAndBit);
        void startTime(String time);
        void setTimeOut();
        void setTimeOutMyItem();
        void setSell(ProductRealTimeModel productRealTimeModel);
        void setBuy(ProductRealTimeModel productRealTimeModel);
        void buttonLoadingShow();
        void buttonLoadingHide();
    }

    interface Presenter {
        void getProductDetail();
        void getMemberProductHistory();
        void getProductDetailAlert();
        void getCoin();
        void stopTime();
        void stopRealTime();
        void buyProduct();

    }
}

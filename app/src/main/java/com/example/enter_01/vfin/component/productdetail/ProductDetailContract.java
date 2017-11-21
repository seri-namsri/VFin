package com.example.enter_01.vfin.component.productdetail;

import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public interface ProductDetailContract {

    interface View extends BaseView {

        void setUpViewProductDetail(ProductModel productDetail);
        void setUpViewProductDetailDetail(String detail);
    }

    interface Presenter {
        void getProductDetail();

        void getProductDetailAlert();

        void buyProduct(int price,String memberIDOwner,String productName,
                          String imageProduct);

    }
}

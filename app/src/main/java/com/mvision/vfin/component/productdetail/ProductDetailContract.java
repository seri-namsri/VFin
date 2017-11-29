package com.mvision.vfin.component.productdetail;

import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;

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

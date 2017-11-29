package com.mvision.vfin.component.buysell.allproduct;


import com.mvision.vfin.base.BaseView;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public interface AllProductContract {

    interface View extends BaseView {

        void setUpViewAllProduct(ArrayList<ProductRealTimeModel>allProduct);
        void removeItem(int position);
        void changeItem(int position);
        void changeItemFail(int position);

    }

    interface Presenter {
        void getProductAll();

        void buyProduct(ProductRealTimeModel productModel);

    }
}

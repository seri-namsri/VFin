package com.example.enter_01.vfin.component.buysell.allproduct;


import com.example.enter_01.vfin.base.BaseView;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public interface AllProductContract {

    interface View extends BaseView {

        void setUpViewAllProduct(ArrayList<ProductModel>allProduct);

    }

    interface Presenter {
        void getProductAll();

        void buyProduct(ProductModel productModel,int price,String memberIdOwner);

    }
}

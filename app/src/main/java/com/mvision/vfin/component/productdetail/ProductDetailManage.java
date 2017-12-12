package com.mvision.vfin.component.productdetail;

import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.firebase.Firestore.Update;

import java.util.ArrayList;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailManage {
    private static ProductDetailManage instance = null;

    public static ProductDetailManage getInstance() {
        if (instance == null)
            instance = new ProductDetailManage();

        return instance;
    }

    private interface CallBackMemberData {
        void memberData(ProductModel productModel);
    }

}


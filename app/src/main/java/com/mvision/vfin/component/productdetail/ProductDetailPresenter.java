package com.mvision.vfin.component.productdetail;

import android.os.Bundle;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.buysell.allproduct.AllProductFragment;
import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.profile.ProfileManager;
import com.mvision.vfin.component.profile.model.Member;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.PreferencesMange;

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

    }

    @Override
    public void getProductDetailAlert() {

        view.setUpViewProductDetailDetail(productModel.getFull_detail());
    }

    @Override
    public void buyProduct(final int price, final String memberIDOwner, final String productName,
                           final String imageProduct) {

    }

}

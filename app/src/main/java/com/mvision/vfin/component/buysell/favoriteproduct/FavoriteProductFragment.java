package com.mvision.vfin.component.buysell.favoriteproduct;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class FavoriteProductFragment extends BaseFragment{

    public static FavoriteProductFragment newInstance() {
        FavoriteProductFragment fragment = new FavoriteProductFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_favorite_product;
    }
}

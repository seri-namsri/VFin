package com.mvision.vfin.customview.dialoglist;

import com.mvision.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 12/4/2017 AD.
 */

public class ReclyViewDialogPresenter extends Presenter<DialogContract.View> implements
        DialogContract.Presenter {

    private DialogContract.View view ;
    public ReclyViewDialogPresenter(DialogContract.View view){
        this.view = view;
    }

    @Override
    public void getDataList() {

    }
}

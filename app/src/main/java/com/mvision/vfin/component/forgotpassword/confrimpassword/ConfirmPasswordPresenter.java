package com.mvision.vfin.component.forgotpassword.confrimpassword;

import com.mvision.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class ConfirmPasswordPresenter extends Presenter<ConfirmPasswordContract.View> implements
        ConfirmPasswordContract.Presenter{

    private ConfirmPasswordContract.View view ;
    public ConfirmPasswordPresenter(ConfirmPasswordContract.View view){
        this.view = view;
    }

    @Override
    public void sentChangePassword() {

    }
}

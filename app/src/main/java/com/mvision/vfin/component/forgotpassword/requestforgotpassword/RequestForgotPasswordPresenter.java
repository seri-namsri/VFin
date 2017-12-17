package com.mvision.vfin.component.forgotpassword.requestforgotpassword;

import com.mvision.vfin.base.presenter.Presenter;

/**
 * Created by enter_01 on 12/14/2017 AD.
 */

public class RequestForgotPasswordPresenter extends Presenter<RequestForgotPasswordContract.View>
        implements RequestForgotPasswordContract.Presenter {

    private RequestForgotPasswordContract.View view ;
    public RequestForgotPasswordPresenter(RequestForgotPasswordContract.View view){
        this.view = view ;
    }
    @Override
    public void sentOtp() {
         view.sentOtpSuccess();
    }
}

package com.mvision.vfin.component.splashscreen.dialoglanguage;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.utility.PreferencesMange;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public class DialogLanguagePresenter extends Presenter<DialogLanguageContract.View> implements
        DialogLanguageContract.Presenter{

    private DialogLanguageContract.View view;

    public DialogLanguagePresenter(DialogLanguageContract.View view){
        this.view = view;
    }


    @Override
    public void selectLanguage(String la) {
        PreferencesMange.getInstance().setLanguage(la);
        view.selectSuccess();
    }
}

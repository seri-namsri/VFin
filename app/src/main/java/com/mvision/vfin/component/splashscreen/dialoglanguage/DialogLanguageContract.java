package com.mvision.vfin.component.splashscreen.dialoglanguage;

import com.mvision.vfin.base.BaseView;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public interface DialogLanguageContract {
    interface View extends BaseView {
        void selectSuccess();
    }

    interface Presenter {
        void selectLanguage(String la);
    }
}

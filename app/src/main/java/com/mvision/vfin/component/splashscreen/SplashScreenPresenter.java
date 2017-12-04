package com.mvision.vfin.component.splashscreen;



import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Utility;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class SplashScreenPresenter extends Presenter<SplashScreenContract.View> implements
        SplashScreenContract.Presenter {

    private SplashScreenContract.View view;

    public SplashScreenPresenter(SplashScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void getAppFirst() {

        if (Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "member_id") != null
                && !Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "member_id")
                .isEmpty()) {
            view.showMainActivity();

        } else {
            view.showLoginActivity();
        }
    }

    @Override
    public void getVersion() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = Contextor.getInstance().getContext().getPackageManager()
                    .getPackageInfo(Contextor.getInstance().getContext().getPackageName(), 0);
            view.showVersion("Version : " + packageInfo.versionName+"");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


}

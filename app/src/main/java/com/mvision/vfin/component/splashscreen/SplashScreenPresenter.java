package com.mvision.vfin.component.splashscreen;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.Utility;

import java.util.Locale;

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
        if (PreferencesMange.getInstance().getLanguage().isEmpty())
            view.showSelectLanguage();
        else
            getCheckLanguage();

    }

    public void setLocale(String lang) { //call this in onCreate()
        Locale myLocale = new Locale(lang);
        Resources res = Contextor.getInstance().getContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }

    @Override
    public void getVersion() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = Contextor.getInstance().getContext().getPackageManager()
                    .getPackageInfo(Contextor.getInstance().getContext().getPackageName(), 0);
            view.showVersion("Version : " + packageInfo.versionName + "");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCheckLanguage() {
        if (!PreferencesMange.getInstance().getLanguage().isEmpty()) {
            setLocale(PreferencesMange.getInstance().getLanguage());
            checkGoto();
        }

    }

    private void checkGoto() {
        if (Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "member_id") !=
                null
                && !Utility.loadSavedPreferences(Contextor.getInstance().getContext(), "member_id")
                .isEmpty()) {
            view.showMainActivity();

        } else {
            view.showLoginActivity();
        }
    }


}

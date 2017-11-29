package com.mvision.vfin;

import android.support.multidex.MultiDexApplication;

import com.mvision.vfin.utility.Contextor;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class ApplicationVFin  extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
       // MultiDex.install(this);
        Contextor.getInstance().init(getApplicationContext());
    }
}

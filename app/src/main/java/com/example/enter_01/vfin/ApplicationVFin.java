package com.example.enter_01.vfin;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.enter_01.vfin.utility.Contextor;

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

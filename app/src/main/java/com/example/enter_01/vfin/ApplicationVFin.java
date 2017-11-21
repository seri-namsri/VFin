package com.example.enter_01.vfin;

import android.app.Application;

import com.example.enter_01.vfin.utility.Contextor;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class ApplicationVFin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }
}

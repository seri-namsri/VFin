package com.example.enter_01.vfin.utility;

import android.content.Context;

/**
 * Created by Hytexts_Android on 13/3/2560.
 */
public class Contextor {
    private static Contextor instance = null;

    public static Contextor getInstance() {
        if (instance == null)
            instance = new Contextor();

        return instance;
    }

    private Context mContext;

    private Contextor() {
    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }


}

package com.mvision.vfin.firebase.Firestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mvision.vfin.utility.Log;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Insert {
    private static Insert instance = null;

    public static Insert getInstance() {
        if (instance == null)
            instance = new Insert();

        return instance;
    }



    public interface CallBackData{
        <T>  void onSuccess(T t);
        <T>  void onSuccessAll(ArrayList<T> tArrayList);
        void onFail(String error);
    }


}

package com.mvision.vfin.firebase.Firestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.utility.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Update {
    private static Update instance = null;

    public static Update getInstance() {
        if (instance == null)
            instance = new Update();

        return instance;
    }


    public interface CallBackData {
        <T> void onSuccess(T t);

        <T> void onSuccessAll(ArrayList<T> tArrayList);

        void onFail(String error);
    }


}

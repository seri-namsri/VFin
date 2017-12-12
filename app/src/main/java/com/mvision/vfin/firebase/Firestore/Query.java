package com.mvision.vfin.firebase.Firestore;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mvision.vfin.utility.Log;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class Query {
    private static Query instance = null;

    public static Query getInstance() {
        if (instance == null)
            instance = new Query();

        return instance;
    }


    public interface CallBackData{
        <T>  void onSuccess(T t);
        <T>  void onSuccessAll(ArrayList<T> tArrayList);
        void onFail(String error);
    }

    public interface CallBackDataRealTime{
        <T>  void onSuccess(T t);
        <T>  void onSuccessRemove(int position);
        <T>  void onSuccessItemChange(int position);
        <T>  void onSuccessAll(ArrayList<T> tArrayList);
        void onFail(String error);
    }


    public interface CallBackDataTrade{
        <T>  void onSuccess(T t);
        <T>  void onSuccessAll(ArrayList<T> tArrayList);
        void onFail(String error);
        void onItemFail(int position);
    }


}

package com.mvision.vfin.error;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.ErrorModel;
import com.mvision.vfin.base.BaseActivity;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.mvision.vfin.utility.Utility;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by enter_01 on 12/6/2017 AD.
 */

public class ErrorMange {
    private static ErrorMange instance = null;

    public static ErrorMange getInstance() {
        if (instance == null)
            instance = new ErrorMange();

        return instance;
    }


    private ErrorModel errorModel;


    public void setError(Throwable e, final CallBackError callBackError) {
        e.printStackTrace();

        if (e instanceof HttpException) {
            dismissProgress();
            ResponseBody body = ((HttpException) e).response().errorBody();


            try {
                errorModel = new Gson().fromJson(body
                        .string(), ErrorModel.class);

               // Log.e("errorModel", new Gson().toJson(errorModel));
                if (errorModel.errorCode.equals("2018")) {
                    PreferencesMange.getInstance().removeMemberID();
                    PreferencesMange.getInstance().removeTokenSession();
                    BaseActivity.activity.finish();
                    Intent i = Contextor.getInstance().getContext().getPackageManager().
                            getLaunchIntentForPackage(Contextor.getInstance().getContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Contextor.getInstance().getContext().startActivity(i);
                    Utility.ShowMsg(Contextor.getInstance().getContext(), errorModel.message + "");
                } else {
                    showAlertCustom(errorModel.message + "[" + errorModel.errorCode + "]",
                            "ลองอีกครั้ง", "ยกเลิก",
                            false, new
                                    CallBackAlertClick() {
                                        @Override
                                        public void clickOk() {
                                            callBackError.reloadConnect();
                                            showProgress("รอสักครู่");
                                        }

                                        @Override
                                        public void clickCancel() {
                                            callBackError.clickCancel();
                                        }
                                    });
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                if (errorModel != null && errorModel.status == 404) {

                    showAlertCustom("Can't Connect", "ลองอีกครั้ง", "ยกเลิก", false, new
                            CallBackAlertClick() {
                                @Override
                                public void clickOk() {
                                    callBackError.reloadConnect();

                                    showProgress("รอสักครู่");
                                }

                                @Override
                                public void clickCancel() {

                                       callBackError.clickCancel();
                                }
                            });
                    //   Utility.ShowMsg(Contextor.getInstance().getContext(), "Can't Connect");
                }
            }
        }

    }

    private ProgressDialog mProgressDialog;

    protected void showProgress(String msg) {

        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(BaseActivity.activity, Contextor.getInstance()
                .getContext().getResources()
                .getString(R
                        .string
                        .app_name), msg);

    }


    protected void dismissProgress() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getError() {
        if (errorModel != null)
            return errorModel.message + "";
        return "";
    }

    public interface CallBackError {
        void reloadConnect();

        void clickCancel();
    }

    public interface CallBackAlertClick {
        void clickOk();

        void clickCancel();
    }

    protected void showAlertCustom(String msg, String text_yes, String text_no, boolean setcancle, final CallBackAlertClick callBackAlertClick) {
        Typeface face = Typeface.createFromAsset(Contextor.getInstance().getContext().getAssets(), "fonts/Kanit-Light.otf");

        final AlertDialog builder = new AlertDialog.Builder(BaseActivity.activity)
                .setTitle(Contextor.getInstance().getContext().getResources().getString(R.string
                        .app_name))
                .setMessage(msg)
                .setCancelable(setcancle)
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(text_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        callBackAlertClick.clickOk();
                    }
                })
                .setNegativeButton(text_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        callBackAlertClick.clickCancel();
                    }
                }).show();

        TextView message = builder.findViewById(android.R.id.message);
        Button button1 = builder.findViewById(android.R.id.button1);
        Button button2 = builder.findViewById(android.R.id.button2);
        Button button3 = builder.findViewById(android.R.id.button3);
        message.setTypeface(face);
        button1.setTypeface(face);
        button2.setTypeface(face);
        button3.setTypeface(face);
    }

}

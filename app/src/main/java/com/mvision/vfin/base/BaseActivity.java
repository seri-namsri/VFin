package com.mvision.vfin.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.TextView;


import com.mvision.vfin.R;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.PreferencesMange;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    public static Activity activity ;
    protected Toolbar mToolBar;
    private ProgressDialog mProgressDialog;
    private Unbinder unbinder;
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceIdentifier());
     //   loadInfoToolbar();
      //  activity = this;
        unbinder = ButterKnife.bind(this);
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
        startView();

    }

    protected void showProgress( String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name),
                msg);

    }


    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
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
    protected void onStart() {
        super.onStart();
        activity = this;
        setLocale(PreferencesMange.getInstance().getLanguage());
    }

    @Override
    protected void onDestroy() {
        try {
            super.onDestroy();
            unbinder.unbind();
        }catch (Exception e){}

    }


  /*  private void loadInfoToolbar() {
        try {
            // mToolBar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolBar);
            getSupportActionBar().setTitle(getTitleToolBar());
            getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());

        } catch (Exception e) {
            //   e.printStackTrace();
        }
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    protected abstract int setLayoutResourceIdentifier();

    protected abstract void initializePresenter();

    protected abstract void startView();


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Runtime.getRuntime().gc();
    }


    protected void startActivityFromActivity(Class classN, Bundle data, boolean flag) {
        Intent intent = new Intent(this, classN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (data != null)
            intent.putExtras(data);
        startActivity(intent);
    }


    protected void startActivityResultFromActivity(Class classN, Bundle data, int requestCode) {
        Intent intent = new Intent(this, classN);
        if (data != null)
            intent.putExtras(data);
        startActivityForResult(intent, requestCode);
    }


    protected void startActivityFromActivity(Class classN, Bundle data) {
        Intent intent = new Intent(this, classN);
        if (data != null)
            intent.putExtras(data);
        startActivity(intent);
    }


}

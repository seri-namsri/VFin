package com.example.enter_01.vfin.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.enter_01.vfin.base.presenter.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected Toolbar mToolBar;
    private ProgressDialog mProgressDialog;
    private Unbinder unbinder;
    protected Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceIdentifier());
     //   loadInfoToolbar();
        unbinder = ButterKnife.bind(this);
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getIntent().getExtras());
        }
        startView();
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

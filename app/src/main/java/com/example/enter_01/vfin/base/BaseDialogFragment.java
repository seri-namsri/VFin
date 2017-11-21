package com.example.enter_01.vfin.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.enter_01.vfin.base.presenter.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by kosian on 4/6/2016.
 */
public abstract class BaseDialogFragment extends DialogFragment implements BaseView {
    public BaseDialogFragment() {
        super();
    }

    protected Toolbar mToolBar;
    protected FragmentManager fragmentManager;
    private View view;
    protected Presenter presenter;
    private Unbinder unbinder;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        setHasOptionsMenu(true);
        initializePresenter();
        if (presenter != null) {
            presenter.initialize(getArguments());
            if (savedInstanceState != null) {
                presenter.initializeSaveStaste(savedInstanceState);
            }
        }


    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutResourceIdentifier(), container, false);
        unbinder = ButterKnife.bind(this, view);
        startView();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Runtime.getRuntime().gc();
        unbinder.unbind();

    }

    protected abstract void initializePresenter();

    protected abstract void startView();

    protected abstract int setLayoutResourceIdentifier();



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}

package com.example.enter_01.vfin.base;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.enter_01.vfin.base.presenter.Presenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public abstract class BaseFragment extends Fragment implements BaseView{


    protected FragmentManager fragmentManager;
    private ProgressDialog mProgressDialog;
    private View view;
    protected Presenter presenter;
    private Unbinder unbinder;

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutResourceIdentifier(), container, false);
        unbinder = ButterKnife.bind(this, view);
      //  loadInfoToolbar();
        startView();
        return view;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
      //  unbinder.unbind();

    }



  /*  private void loadInfoToolbar() {
        try {
         //   mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolBar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getTitleToolBar());
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(getDisplayHomeAsUpEnabled());
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(getDisplayHomeAsUpEnabled());

        } catch (Exception e) {
            //   e.printStackTrace();
        }
    }*/

    public void applyFontForToolbarTitle(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(getActivity().getTitle())) {
                    tv.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Kanit-Regular.otf"));
                    break;
                }
            }
        }
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

    protected void startActivityFromFragment(Class classN, Bundle data) {
        Intent intent = new Intent(getActivity(), classN);
        if (data != null)
            intent.putExtras(data);
        startActivity(intent);
    }

    protected void startActivityFromFragmentPretest(Class classN, Bundle data) {
        Intent intent = new Intent(getActivity(), classN);
        if (data != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtras(data);
        }
        startActivity(intent);
    }
}

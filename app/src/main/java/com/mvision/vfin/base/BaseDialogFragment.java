package com.mvision.vfin.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.utility.Contextor;

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

    protected void startActivityFromFragment(Class classN, Bundle data) {
        Intent intent = new Intent(getActivity(), classN);
        if (data != null)
            intent.putExtras(data);
        startActivity(intent);
    }

    protected void startActivityResultFromFragment(Class classN, Bundle data, int requestCode) {
        Intent intent = new Intent(getActivity(), classN);
        if (data != null)
            intent.putExtras(data);
        startActivityForResult(intent, requestCode);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutResourceIdentifier(), container, false);
        unbinder = ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        startView();
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Runtime.getRuntime().gc();
        unbinder.unbind();

    }

    private ProgressDialog mProgressDialog;
    protected void showProgress( String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            dismissProgress();

        mProgressDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.app_name),
                msg);

    }


    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
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

}

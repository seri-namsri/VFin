package com.mvision.vfin.component.splashscreen;

import android.os.Handler;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.login.LoginActivity;
import com.mvision.vfin.component.main.MainActivity;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class SplashScreenFragment extends BaseFragment implements SplashScreenContract.View{

    public static  SplashScreenFragment newInstance() {
        SplashScreenFragment fragment = new SplashScreenFragment();
        return fragment;
    }

    private SplashScreenPresenter presenter ;


    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initializePresenter() {
        presenter = new SplashScreenPresenter(this);
        super.presenter=  presenter;
    }
    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 1000L;

    @Override
    protected void startView() {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                presenter.getAppFirst();
            }
        };
        
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_splash_screen;
    }

    @Override
    public void showMainActivity() {
          getActivity().finish();
          startActivityFromFragment(MainActivity.class,null);
    }

    @Override
    public void showLoginActivity() {
        getActivity().finish();
        startActivityFromFragment(LoginActivity.class,null);
    }
}

package com.mvision.vfin.component.myproduct.myproductmain;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.financehistory.FinanceHistoryActivity;
import com.mvision.vfin.component.myproduct.tradingclose.TradingCloseFragment;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductMainFragment extends BaseFragment implements ViewPager
        .OnPageChangeListener, MyProductMainContract.View {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private static ViewPager viewPager = null;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MyProductMainPager myProductMainPager;
    private MyProductMainPresenter presenter;

    public static MyProductMainFragment newInstance(Bundle bundle) {
        MyProductMainFragment fragment = new MyProductMainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

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
        presenter = new MyProductMainPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getFirstView();
    }

    private void setUpPager(final int changeView) {
        if (viewPager == null) {
            viewPager = getViewLayout().findViewById(R.id.viewPager);
            myProductMainPager = new MyProductMainPager(getChildFragmentManager(), callBackMangePager);
            viewPager.setAdapter(myProductMainPager);
            if (changeView != 1)
                viewPager.setCurrentItem(1);
            tabLayout.setupWithViewPager(viewPager);
            changeTabsFont(tabLayout);
            viewPager.addOnPageChangeListener(this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (changeView != 1)
                        viewPager.setCurrentItem(0);
                    else
                        viewPager.setCurrentItem(1);
                }
            }, 100);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewPager = null;
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_product_main;
    }

    public void changeTabsFont(TabLayout layoutWithFont) {
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Kanit-Light.otf");
        ViewGroup vg = (ViewGroup) layoutWithFont.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
    }


    private void setUpToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Contextor.getInstance
                ().getContext().getResources().getString(R.string.MyProductMainFragmentTitle));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Fragment fragment = myProductMainPager.getFragment(position);
        if (fragment != null) {
            TradingCloseFragment tradingCloseFragment = (TradingCloseFragment) fragment;
            tradingCloseFragment.start();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private CallBackMangePager callBackMangePager = new CallBackMangePager() {
        @Override
        public void reLoad(int position) {
            Fragment fragment = myProductMainPager.getFragment(position);
            if (fragment != null) {
                TradingCloseFragment tradingCloseFragment = (TradingCloseFragment) fragment;
                tradingCloseFragment.start();
            }
        }

        @Override
        public void changePage(int position) {
            if (viewPager != null)
                viewPager.setCurrentItem(position);
        }

        @Override
        public void startWalletHistory() {
            Intent intent = new Intent(Contextor.getInstance().getContext(), FinanceHistoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Contextor.getInstance().getContext().startActivity(intent);
        }

    };

    @Override
    public void setUpView(int changeView) {
        setUpPager(changeView);
        setUpToolbar();
    }

    public interface CallBackMangePager {
        void reLoad(int position);

        void changePage(int position);

        void startWalletHistory();
    }
}

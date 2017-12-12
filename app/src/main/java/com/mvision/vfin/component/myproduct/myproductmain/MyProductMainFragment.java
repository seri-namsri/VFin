package com.mvision.vfin.component.myproduct.myproductmain;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/24/2017 AD.
 */

public class MyProductMainFragment extends BaseFragment {

    @BindView(R.id.tabLayout)TabLayout tabLayout;
    @BindView(R.id.viewPager)ViewPager viewPager;
    @BindView(R.id.toolbar)Toolbar toolbar;

    public static MyProductMainFragment newInstance() {
        MyProductMainFragment fragment = new MyProductMainFragment();
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

    }

    @Override
    protected void startView() {
        setUpPager();
        setUpToolbar();
    }

    private void setUpPager(){
        MyProductMainPager myProductMainPager = new MyProductMainPager(getChildFragmentManager());
        viewPager.setAdapter(myProductMainPager);
        tabLayout.setupWithViewPager(viewPager);
        changeTabsFont(tabLayout);
    }


    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_my_product_main;
    }

    public void changeTabsFont(TabLayout layoutWithFont) {
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Kanit-Regular.otf");
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


    private void setUpToolbar(){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("สินค้าของฉัน");
    }

}

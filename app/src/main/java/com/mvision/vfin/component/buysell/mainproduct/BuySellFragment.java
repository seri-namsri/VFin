package com.mvision.vfin.component.buysell.mainproduct;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class BuySellFragment extends BaseFragment {

    @BindView(R.id.tabLayout)TabLayout tabLayout;
    @BindView(R.id.viewPager)ViewPager viewPager;

    public static BuySellFragment newInstance() {
        BuySellFragment fragment = new BuySellFragment();
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
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_buysell;
    }

    private void setUpPager(){
        BuySellPager buySellPager = new BuySellPager(getChildFragmentManager());
        viewPager.setAdapter(buySellPager);
        tabLayout.setupWithViewPager(viewPager);
        changeTabsFont(tabLayout);
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

}

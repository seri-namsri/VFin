package com.mvision.vfin.component.myproduct.myproductmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.mvision.vfin.R;
import com.mvision.vfin.component.myproduct.tradingclose.TradingCloseFragment;
import com.mvision.vfin.utility.Contextor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MyProductMainPager extends FragmentPagerAdapter {

    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private MyProductMainFragment.CallBackMangePager callBackMangePager ;
    public MyProductMainPager(FragmentManager fm, MyProductMainFragment.CallBackMangePager callBackMangePager) {
        super(fm);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
        this.callBackMangePager = callBackMangePager;
    }

    @Override
    public Fragment getItem(int position) {
        String type = "";
        if (position == 0) {
            type = "closed";
        } else if (position == 1) {
            type = "fee_paid";
        } else if (position == 2) {
            type = "delivery";
        }
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        return TradingCloseFragment.newInstance(bundle,callBackMangePager);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_0);
        }  else if (position == 1) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_2);
        } else if (position == 2) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_3);
        }
        return "";
    }


    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }
        return object;
    }

    public Fragment getFragment(int position) {
        Fragment fragment = null;
        String tag = mFragmentTags.get(position);
        if (tag != null) {
            fragment = mFragmentManager.findFragmentByTag(tag);
        }
        return fragment;
    }
}

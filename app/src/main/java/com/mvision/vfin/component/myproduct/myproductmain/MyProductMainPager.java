package com.mvision.vfin.component.myproduct.myproductmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.mvision.vfin.R;
import com.mvision.vfin.component.myproduct.tradingclose.TradingCloseFragment;
import com.mvision.vfin.utility.Contextor;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MyProductMainPager extends FragmentPagerAdapter {


    public MyProductMainPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String type = "";
        if (position == 0) {
            type = "closed";
        } else if (position == 1) {
            type = "address_selected";
        } else if (position == 2) {
            type = "fee_paid";
        } else if (position == 3) {
            type = "delivery";
        }
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        return TradingCloseFragment.newInstance(bundle);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_0);
        } else if (position == 1) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_1);
        } else if (position == 2) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_2);
        } else if (position == 3) {
            return Contextor.getInstance().getContext().getResources().getString(R.string.MyProductMainPager_3);
        }
        return "";
    }


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     //   super.destroyItem(container, position, object);
    }
}

package com.example.enter_01.vfin.component.buysell.mainproduct;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.enter_01.vfin.component.buysell.allproduct.AllProductFragment;
import com.example.enter_01.vfin.component.buysell.favoriteproduct.FavoriteProductFragment;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class BuySellPager extends FragmentPagerAdapter {


    public BuySellPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return AllProductFragment.newInstance();
        } else {
         //   return FavoriteProductFragment.newInstance();
            return AllProductFragment.newInstance();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "รายการทั้งหมด";
        } else {
            return "รายการโปรด";
        }
    }


    @Override
    public int getCount() {
        return 2;
    }
}

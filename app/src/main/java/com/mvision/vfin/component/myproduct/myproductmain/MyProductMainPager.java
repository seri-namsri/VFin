package com.mvision.vfin.component.myproduct.myproductmain;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mvision.vfin.component.buysell.allproduct.AllProductFragment;
import com.mvision.vfin.component.myproduct.myproductprocess.MyProductProcessFragment;
import com.mvision.vfin.component.myproduct.myproducttransport.MyProductTransportFragment;
import com.mvision.vfin.component.myproduct.myproductwaittransport.MyProductWaitTransportFragment;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MyProductMainPager extends FragmentPagerAdapter {


    public MyProductMainPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MyProductProcessFragment.newInstance();
        } else  if (position == 1) {
            return MyProductWaitTransportFragment.newInstance();
        } else  if (position == 2) {
            return MyProductTransportFragment.newInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "สินค้าดำเนินการ";
        } else  if (position == 1) {
            return "สินค้ารอจัดส่ง";
        } else  if (position == 2) {
            return "สินค้าจัดส่ง";
        }
        return "";
    }


    @Override
    public int getCount() {
        return 3;
    }
}

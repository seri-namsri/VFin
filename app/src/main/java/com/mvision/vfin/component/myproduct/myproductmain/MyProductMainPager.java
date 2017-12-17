package com.mvision.vfin.component.myproduct.myproductmain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.mvision.vfin.component.myproduct.tradingclose.TradingCloseFragment;
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
            return "สินค้ารอดำเนินการ";
        } else if (position == 1) {
            return "สินค้ารอจ่ายค่าจัดส่ง";
        } else if (position == 2) {
            return "สินค้าเตรียมจัดส่ง";
        } else if (position == 3) {
            return "สินค้าที่ต้องได้รับ";
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

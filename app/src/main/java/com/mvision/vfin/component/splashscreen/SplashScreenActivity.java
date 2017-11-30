package com.mvision.vfin.component.splashscreen;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseActivity;
import com.mvision.vfin.utility.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by enter_01 on 11/13/2017 AD.
 */

public class SplashScreenActivity extends BaseActivity {
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
    protected int setLayoutResourceIdentifier() {
        return R.layout.activity_base;
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected void startView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, SplashScreenFragment.newInstance());
        transaction.commit();

   /*     PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.mvision.dooads", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/
    }
}

package com.mvision.vfin.component.configkey;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import com.mvision.vfin.utility.Contextor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by enter_01 on 12/24/2017 AD.
 */

public class GetKey {

    private static GetKey instance = null;

    public static GetKey getInstance() {
        if (instance == null)
            instance = new GetKey();
        System.loadLibrary("vfin-security");
        return instance;
    }


    public String getSignatures() {
        try {
            PackageInfo info = Contextor.getInstance().getContext().getPackageManager()
                    .getPackageInfo
                    (Contextor.getInstance().getContext().getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String strDigest = new String(Base64.encode(md.digest(), 0));
                return strDigest.trim();
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        return "";
    }

    public native String getBaseApi(String keyHash);
    //Member
    public native String getKeyAes(String keyHash);
    public native String getIvKey(String keyHash);
    public native String apiLogin(String keyHash);
    public native String apiLoginFaceBook(String keyHash);
    public native String apiSaveAddress(String keyHash);
    public native String apiGetAddress(String keyHash);
    public native String apiUpdateAddressIsPrimary(String keyHash);
    public native String apiGetMember(String keyHash);
    public native String apiUpdateMemberProfile(String keyHash);
    public native String apiForgotPassword(String keyHash);
    public native String apiVerifyOtp(String keyHash);
    public native String apiUpdatePassword(String keyHash);
    public native String apiVerifyAccount(String keyHash);
    public native String apiRegister(String keyHash);
    //Wallet
    public native String apiGetWalletTransaction(String keyHash);
    //Market
    public native String apiListRedemption(String keyHash);
    //Oreder
    public native String apiCalFee(String keyHash);
    public native String apiCreateOrder(String keyHash);
    public native String apiTradeBuy(String keyHash);
    //Product
    public native String apiTradeBid(String keyHash);
    public native String apiFindMyItem(String keyHash);
    public native String apiTradeProduct(String keyHash);
    //Message
    public native String apiMessageList(String keyHash);
    public native String apiMessageDelete(String keyHash);

}

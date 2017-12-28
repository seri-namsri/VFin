package com.mvision.vfin.component.myproduct.tradingclose.dialogcalculateprice;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.response.OrderCalFeeResponseModel;
import com.mvision.vfin.base.BaseDialogFragment;
import com.mvision.vfin.component.myproduct.myproductmain.MyProductMainFragment;
import com.mvision.vfin.component.myproduct.tradingclose.pojo.MyproductModel;
import com.mvision.vfin.component.splashscreen.dialoglanguage.DialogLanguageContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/18/2017 AD.
 */

public class DialogCalculatePriceDialogFragment extends BaseDialogFragment implements
        DialogCalculatePriceContract.View {

    private static MyProductMainFragment.CallBackMangePager callBackMangePagerTrad;
    @BindView(R.id.buttonWalletHistory)
    Button buttonWalletHistory;
    @BindView(R.id.buttonMyProduct)
    Button buttonMyProduct;
    @BindView(R.id.buttonTopUp)
    Button buttonTopUp;
    @BindView(R.id.buttonCancel)
    Button buttonCancel;
    @BindView(R.id.buttonBuy)
    Button buttonBuy;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private DialogCalculatePricePresenter presenter;

    public static DialogCalculatePriceDialogFragment newInstance(Bundle bundle, MyProductMainFragment.CallBackMangePager callBackMangePager) {
        callBackMangePagerTrad = callBackMangePager;
        DialogCalculatePriceDialogFragment fragment = new DialogCalculatePriceDialogFragment();
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
        presenter = new DialogCalculatePricePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getPrice();
        setCancelable(false);
    }

    @OnClick({R.id.buttonCancel, R.id.buttonWalletHistory, R.id.buttonMyProduct, R.id.buttonTopUp, R.id.buttonBuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCancel:
                   dismiss();
                break;
            case R.id.buttonWalletHistory:
                // dismiss();
                callBackMangePagerTrad.startWalletHistory();
                break;
            case R.id.buttonMyProduct:
                dismiss();
                callBackMangePagerTrad.changePage(1);
                break;
            case R.id.buttonTopUp:
                dismiss();
                // callBackMangePagerTrad.reLoad(0);
                break;
            case R.id.buttonBuy:
                presenter.getCreateOrder();
                // callBackMangePagerTrad.reLoad(0);
                break;

        }

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.dialog_fragment_calculate_price;
    }


    @Override
    public void showDialogloading() {
        buttonCancel.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        buttonMyProduct.setVisibility(View.GONE);
        buttonWalletHistory.setVisibility(View.GONE);
        buttonBuy.setVisibility(View.GONE);
        buttonTopUp.setVisibility(View.GONE);
        textView.setText("");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showButtonHaveWallet(int priceProduct,int shippingPrice,int systemFeePrice,
                                     int totalPrice) {

        buttonCancel.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        buttonMyProduct.setVisibility(View.GONE);
        buttonWalletHistory.setVisibility(View.GONE);
        buttonBuy.setVisibility(View.VISIBLE);
        buttonTopUp.setVisibility(View.GONE);
        String title = "คำนวนราคา รวมค่าจัดส่ง";
        String vinPoint = "vin point";
        String totalString = title+"\nราคาสินค้า : " + priceProduct+" "
                + vinPoint+" \nค่าจัดส่ง : " + shippingPrice + " " +vinPoint+
                "\nค่าธรรมเนีนม : " + systemFeePrice + " " +vinPoint+
                "\nรวม : " + totalPrice +
                " "+vinPoint;
        Spannable spanText = new SpannableString(totalString);
        spanText.setSpan(new RelativeSizeSpan(1.5f), 0,title.length(), 0); // set size
        spanText.setSpan(new ForegroundColorSpan(getResources()
                .getColor(R.color.colorPrimaryDark)), 0, title.length(), 0);
        textView.setText(spanText);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stopRealTime();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showButtonNoWallet(int priceProduct,int shippingPrice,int systemFeePrice,
                                   int totalPrice) {
        buttonCancel.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        buttonBuy.setVisibility(View.GONE);
        buttonTopUp.setVisibility(View.VISIBLE);
        buttonMyProduct.setVisibility(View.GONE);
        buttonWalletHistory.setVisibility(View.GONE);

        String title = "คุณมี vin point ไม่พอสำหรับชำระค่าจัดส่ง";
        String vinPoint = "vin point";
        String totalString = title+"\nราคาสินค้า : " + priceProduct
                + vinPoint+" \nค่าจัดส่ง : " + shippingPrice + " " +vinPoint+
                "\nค่าธรรมเนีนม : " + systemFeePrice + " " +vinPoint+
                "\nรวม : " + totalPrice +
                " "+vinPoint;
        Spannable spanText = new SpannableString(totalString);
        spanText.setSpan(new RelativeSizeSpan(1.5f), 0,title.length(), 0); // set size
        spanText.setSpan(new ForegroundColorSpan(Color.RED), 0, title.length(), 0);
        textView.setText(spanText);
    }

    @Override
    public void showShipPing(int priceProduct, int shippingPrice, int systemFeePrice, int totalPrice) {
        buttonCancel.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        buttonMyProduct.setVisibility(View.GONE);
        buttonWalletHistory.setVisibility(View.GONE);
        buttonBuy.setVisibility(View.VISIBLE);
        buttonTopUp.setVisibility(View.GONE);
        String title = "ราคาค่าจัดส่ง";
        String vinPoint = "vin point";
        String totalString = title+"\nราคาสินค้า : " + priceProduct+" "
                + vinPoint+" \nค่าจัดส่ง : " + shippingPrice + " " +vinPoint+
                "\nค่าธรรมเนีนม : " + systemFeePrice + " " +vinPoint+
                "\nรวม : " + totalPrice +
                " "+vinPoint;
        Spannable spanText = new SpannableString(totalString);
        spanText.setSpan(new RelativeSizeSpan(1.5f), 0,title.length(), 0); // set size
        spanText.setSpan(new ForegroundColorSpan(getResources()
                .getColor(R.color.colorPrimaryDark)), 0, title.length(), 0);
        textView.setText(spanText);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showCreateOrder(int priceProduct,int shippingPrice,int systemFeePrice,
                                int totalPrice) {
        buttonCancel.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        buttonBuy.setVisibility(View.GONE);
        buttonMyProduct.setVisibility(View.VISIBLE);
        buttonWalletHistory.setVisibility(View.VISIBLE);

        String title = "คำสั่งซื้อสำเร็จ";
        String vinPoint = "vin point";
        String totalString = title+"\nราคาสินค้า : " + priceProduct
                + vinPoint+" \nค่าจัดส่ง : " + shippingPrice + " " +vinPoint+
                "\nค่าธรรมเนีนม : " + systemFeePrice + " " +vinPoint+
                "\nรวม : " + totalPrice +
                " "+vinPoint;
        Spannable spanText = new SpannableString(totalString);
        spanText.setSpan(new RelativeSizeSpan(1.5f), 0,title.length(), 0); // set size
        spanText.setSpan(new ForegroundColorSpan(getResources()
                .getColor(R.color.colorPrimaryDark)), 0, title.length(), 0);
        textView.setText(spanText);
    }

    @Override
    public void disDialog() {
        dismiss();
    }

}

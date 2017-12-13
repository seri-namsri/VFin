package com.mvision.vfin.component.productdetail;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.component.productdetail.alertdetail.AlertDetailFragment;
import com.mvision.vfin.component.productdetail.pojo.MemberProductHistory;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.relex.circleindicator.CircleIndicator;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by enter_01 on 11/7/2017 AD.
 */

public class ProductDetailFragment extends BaseFragment implements ProductDetailContract.View {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewDetail)
    TextView textViewDetail;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imageViewOwner)
    ImageView imageViewOwner;
    @BindView(R.id.textViewNameOwner)
    TextView textViewNameOwner;
    @BindView(R.id.design_bottom_sheet)
    View design_bottom_sheet;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.priceMarket)
    TextView priceMarket;
    @BindView(R.id.textViewTimeProduct)
    TextView textViewTimeProduct;
    @BindView(R.id.buttonCoin)
    Button buttonCoin;
    @BindView(R.id.buttonVCoin)
    Button buttonVCoin;
    @BindView(R.id.buttonBit)
    Button buttonBit;
    @BindView(R.id.progress)
    ProgressBar progress;

    private ProductDetailPresenter presenter;

    public static ProductDetailFragment newInstance(Bundle bundle) {
        ProductDetailFragment fragment = new ProductDetailFragment();
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
        presenter = new ProductDetailPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getCoin();
        presenter.getProductDetail();
        presenter.getMemberProductHistory();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_product_detail;
    }

    @Override
    public void setUpViewProductDetail(ProductRealTimeModel productDetail) {

        textViewTitle.setText(productDetail.getName());
        textViewDetail.setText("");

        ProductDetailPager productDetailPager = new ProductDetailPager(productDetail
                .getImages());
        viewPager.setAdapter(productDetailPager);
        indicator.setViewPager(viewPager);


        //  imageProduct = productDetail.getImage_product().get(0);
        priceMarket.setText("ราคาตลาด\n" + productDetail.getMarketPrice());
        textViewNameOwner.setText(productDetail.getOwnerName());
        CropCircleTransformation multi = new CropCircleTransformation();
        Glide.with(Contextor.getInstance().getContext())
                .load(productDetail.avatarLink).apply(bitmapTransform(multi))
                .into(imageViewOwner);
        setUpBottomSheet();

       /* if (productDetail.getMember_buy()!=null){
            ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(
                    getMember_buy_sub(productDetail.getMember_buy()));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(productDetailAdapter);



        }else {
            buttonCoin.setText(productDetail.getPrice() + "");
            buttonCoin.setTag(productDetail.getPrice() + "");
        }

      //  priceMarket.setText("ราคาตลาด\n"+productDetail.getPrice_market());*/

    }

    @Override
    public void setUpViewMemberProductHistory(ArrayList<MemberProductHistory> memberProductHistory) {
        ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(memberProductHistory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productDetailAdapter);
    }

    @Override
    public void setUpViewProductDetailDetail(String detail) {
        AlertDetailFragment editNameDialogFragment = AlertDetailFragment.newInstance(detail);
        editNameDialogFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void setUpViewCoin(ModelCoinAndBit modelCoinAndBit) {
        buttonVCoin.setText(modelCoinAndBit.getWallet() + "");
        buttonBit.setText(modelCoinAndBit.getAbility().getCurrent() + "/" + modelCoinAndBit.getAbility
                ().getMax());
    }

    @Override
    public void startTime(String time) {
        textViewTimeProduct.setText(time);
    }

    @Override
    public void setTimeOut() {
        buttonCoin.setText("หมดเวลา");
        textViewTimeProduct.setText("หมดเวลา");
        buttonCoin.setEnabled(false);
        buttonCoin.setBackgroundResource(R.drawable.button_radius);
    }

    @Override
    public void setTimeOutMyItem() {
        buttonCoin.setText("สินค้าเป็นของคุณ");
        textViewTimeProduct.setText("หมดเวลา");
        buttonCoin.setEnabled(false);
        buttonCoin.setBackgroundResource(R.drawable.button_radius);
    }

    @Override
    public void setSell(ProductRealTimeModel productRealTimeModel) {
        buttonCoin.setText("รอคนซื้อ " + productRealTimeModel.getNextPrice() + " Vin point");
        buttonCoin.setEnabled(false);
        buttonCoin.setBackgroundResource(R.drawable.button_radius);
    }

    @Override
    public void setBuy(ProductRealTimeModel productRealTimeModel) {
        buttonCoin.setText("ซื้อเลย " + productRealTimeModel.getNextPrice() + " Vin point");
        buttonCoin.setEnabled(true);
        buttonCoin.setBackgroundResource(R.drawable.button_radius_green);
    }

    @Override
    public void buttonLoadingShow() {
        buttonCoin.setText("");
        progress.setVisibility(View.VISIBLE);
        buttonCoin.setEnabled(false);
        buttonCoin.setBackgroundResource(R.drawable.button_radius);
    }

    @Override
    public void buttonLoadingHide() {
        progress.setVisibility(View.GONE);

    }

    private BottomSheetBehavior mBottomSheetBehavior;

    private void setUpBottomSheet() {
        mBottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet);
        linearLayout.setVisibility(View.VISIBLE);
        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }


    @OnClick({R.id.buttonCoin, R.id.buttonProductDetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCoin:
                  presenter.buyProduct();
                break;
            case R.id.buttonProductDetail:
                presenter.getProductDetailAlert();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.stopTime();
        presenter.stopRealTime();
    }
}

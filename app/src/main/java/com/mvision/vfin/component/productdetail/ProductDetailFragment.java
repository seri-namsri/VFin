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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.buysell.allproduct.pojo.MemberBuy;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.mvision.vfin.component.productdetail.alertdetail.AlertDetailFragment;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;

import java.text.SimpleDateFormat;
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
        presenter.getProductDetail();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_product_detail;
    }

    private String memberIdOwner,productName,imageProduct ;


    public List<MemberBuy> getMember_buy_sub(List<MemberBuy>  member_buy) {
        try {
            return member_buy.subList(member_buy.size() - 5, member_buy.size() );
        } catch (Exception e) {
            return member_buy;
        }
    }
    @Override
    public void setUpViewProductDetail(ProductModel productDetail) {

        textViewTitle.setText(productDetail.getName());
        textViewDetail.setText(productDetail.getDetail());
        ProductDetailPager productDetailPager = new ProductDetailPager(productDetail
                .getImage_product());
        viewPager.setAdapter(productDetailPager);
        indicator.setViewPager(viewPager);
        productName = productDetail.getName();
        imageProduct = productDetail.getImage_product().get(0);
        priceMarket.setText("ราคาตลาด\n"+productDetail.getPrice_market());
        setUpBottomSheet(productDetail);
        if (productDetail.getMember_buy()!=null){
            ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(
                    getMember_buy_sub(productDetail.getMember_buy()));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(productDetailAdapter);

            textViewNameOwner.setText(productDetail.getMember_buy().get(productDetail
                    .getMember_buy().size() - 1).getUsername().substring(0,4)+"xxxxx");
            CropCircleTransformation multi = new CropCircleTransformation();
            Glide.with(Contextor.getInstance().getContext())
                    .load(productDetail.getMember_buy().get(productDetail
                            .getMember_buy().size() - 1).getImageProfile()).apply(bitmapTransform(multi))
                    .into(imageViewOwner);
            memberIdOwner = productDetail.getMember_buy().get(productDetail
                    .getMember_buy().size() - 1).getMember_id();

        }else {
            buttonCoin.setText(productDetail.getPrice() + "");
            buttonCoin.setTag(productDetail.getPrice() + "");
        }

      //  priceMarket.setText("ราคาตลาด\n"+productDetail.getPrice_market());

    }

    @Override
    public void setUpViewProductDetailDetail(String detail) {
        AlertDetailFragment editNameDialogFragment = AlertDetailFragment.newInstance(detail);
        editNameDialogFragment.show(getFragmentManager(), "dialog");
    }

    private BottomSheetBehavior mBottomSheetBehavior;
    private CountDownTimer countDownTimer;

    private void setUpBottomSheet(ProductModel productDetail) {
        mBottomSheetBehavior = BottomSheetBehavior.from(design_bottom_sheet);
        linearLayout.setVisibility(View.VISIBLE);
        mBottomSheetBehavior.setHideable(false);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        if (productDetail.getMember_buy() != null) {
            int price = productDetail.getMember_buy().get(productDetail
                    .getMember_buy().size() - 1).getPrice();
            double totel = (1 / 100.0f) *  price;
            price = (int) (price+Math.ceil(totel));
            //buttonCoin.setText(price + "");
            buttonCoin.setTag(price + "");
            // price = price + totel;
            if (PreferencesMange.getInstance().getMemberID().equals(productDetail.getMember_buy().get(productDetail
                    .getMember_buy().size() - 1).getMember_id())) {
                buttonCoin.setEnabled(false);
                buttonCoin.setText("รอคนซื้อ "+ price +
                        " Vin point");
                buttonCoin.setBackgroundResource(R.drawable.button_radius);
            } else {
                buttonCoin.setText("ซื้อเลย "+ price +
                        " Vin point");
                buttonCoin.setEnabled(true);
                buttonCoin.setTag(productDetail.getPrice() + "");
                buttonCoin.setBackgroundResource(R.drawable.button_radius_green);
            }


        }else {
            buttonCoin.setText("ซื้อเลย "+ productDetail.getPrice() +
                    " Vin point");
            buttonCoin.setTag(productDetail.getPrice() + "");
            buttonCoin.setEnabled(true);
            buttonCoin.setBackgroundResource(R.drawable.button_radius_green);
        }


        //


        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        long tsLong = System.currentTimeMillis() / 1000;

        final long time1 = productDetail.getTime() - tsLong;

        final int millis = (int) time1;
        df.setTimeZone(tz);
        String time = df.format(new Date(millis));
        textViewTimeProduct.setText(time);
        countDownTimer = new CountDownTimer(time1, 1000) {

            public void onTick(long millisUntilFinished) {
                try {
                    int millis = (int) (millisUntilFinished);
                    df.setTimeZone(tz);
                    String time = df.format(new Date(millis));
                    textViewTimeProduct.setText(time);
                } catch (Exception e) {
                }

            }

            public void onFinish() {
                Log.e("done!");
            }
        }.start();


    }

    @OnClick({R.id.buttonCoin, R.id.buttonProductDetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCoin:
                presenter.buyProduct(Integer.parseInt(buttonCoin.getTag().toString()),
                        memberIdOwner,productName,imageProduct);
                break;
            case R.id.buttonProductDetail:
                presenter.getProductDetailAlert();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}

package com.mvision.vfin.component.buysell.allproduct;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.TimeResponseModel;
import com.mvision.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.mvision.vfin.component.main.model.ModelCoinAndBit;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Contextor;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<ProductRealTimeModel> productModel;
    private CallBackClick callBackClick;
    private ModelCoinAndBit modelCoinAndBit ;
    ProductAdapter(ArrayList<ProductRealTimeModel> productModel, CallBackClick
            callBackClick) {
        this.productModel = productModel;
        this.callBackClick = callBackClick;
        AllProductManage.getInstance().getCoin(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                modelCoinAndBit = (ModelCoinAndBit) t;
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });


    }


    void addData(ArrayList<ProductRealTimeModel> productModel) {
        this.productModel = productModel;
        notifyDataSetChanged();
    }

    void dataChange(int position) {
        //  checkClick = 0 ;
        notifyItemChanged(position);
    }




    void dataChangeFail(int position) {
        //  checkClick = 1;
        notifyItemChanged(position);
    }


    void removeData(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_product, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        setDataOrtherItem(productModel.get(position), holder);
    }


    private void setDataOrtherItem(final ProductRealTimeModel productModel, final ViewHolder holder) {
        holder.textViewNameProduct.setText(productModel.getName());
        holder.priceMarket.setText("ราคาตลาด\n" + productModel.getMarketPrice());

        Glide.with(Contextor.getInstance().getContext()).load(productModel
                .getMainImage())
                .into(holder.imageViewProduct);



    //    holder.buttonPrice.setText("รอคนซื้อ " + productModel.getNextPrice() +
     //           " Vin point");
     //   holder.buttonPrice.setTag(R.string.key_price, productModel.getNextPrice() + "");
      //  holder.buttonPrice.setTag(R.string.key_memberID, "");


        AllProductManage.getInstance().getTime(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                if (holder.countDownTimer != null) {
                    holder.countDownTimer.cancel();
                }
                TimeResponseModel timeResponseModel = (TimeResponseModel) t;
                final long time1 = productModel.getExpiredTime() - timeResponseModel.result;

                final int millis = (int) time1;
                holder.df.setTimeZone(holder.tz);
                String time = holder.df.format(new Date(millis));
                holder.textViewTimeProduct.setText(time);
                holder.countDownTimer = new CountDownTimer(time1, 1000) {

                    public void onTick(long millisUntilFinished) {
                        int millis = (int) (millisUntilFinished);
                        holder.df.setTimeZone(holder.tz);
                        String time = holder.df.format(new Date(millis));
                        holder.textViewTimeProduct.setText(time);
                    }

                    public void onFinish() {
                        if (checkItemOwner(productModel.ownerCode)){
                            holder.buttonPrice.setText("สินค้าเป็นของคุณ");
                        }else {
                            holder.buttonPrice.setText("หมดเวลา");
                        }
                        holder.textViewTimeProduct.setText("หมดเวลา");
                        holder.buttonPrice.setEnabled(false);
                        holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
                    }
                }.start();

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        callBackClick.clickItemProduct(productModel);
                    }
                });
                holder.progress.setVisibility(View.GONE);
                holder.buttonPrice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (modelCoinAndBit.getAbility().current <= 0){
                            callBackClick.clickItemFail("จำนวนบิตไม่พอ");
                        }else if (modelCoinAndBit.getWallet() <= productModel.getNextPrice()){
                            callBackClick.clickItemFail("จำนวนเงินไม่พอ");
                        }else {
                            holder.buttonPrice.setEnabled(false);
                            holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
                            holder.buttonPrice.setText("");
                            holder.progress.setVisibility(View.VISIBLE);
                            callBackClick.clickItemProductBuy(productModel, Long.parseLong(holder
                                            .buttonPrice
                                            .getTag(R.string.key_price)
                                            .toString())
                                    , holder.buttonPrice.getTag(R.string.key_memberID).toString());
                        }

                    }
                });

                if (productModel.getExpiredTime() < timeResponseModel.result){
                    //   holder.textViewTimeProduct.setText("หมดเวลา");
                    if (checkItemOwner(productModel.ownerCode)){
                        holder.buttonPrice.setText("สินค้าเป็นของคุณ");
                    }else {
                        holder.buttonPrice.setText("หมดเวลา");
                    }
                    holder.buttonPrice.setEnabled(false);
                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
                }
                else if (checkItemOwner(productModel.ownerCode)) {
                    holder.buttonPrice.setText("รอคนซื้อ " + productModel.getNextPrice() +" Vin point");
                    holder.buttonPrice.setTag(R.string.key_price, productModel.getNextPrice() + "");
                    holder.buttonPrice.setTag(R.string.key_memberID, productModel.getOwnerCode() + "");
                    holder.buttonPrice.setEnabled(false);
                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
                } else {
                    holder.buttonPrice.setText("ซื้อเลย " + productModel.getNextPrice() +" Vin point");
                    holder.buttonPrice.setTag(R.string.key_price, productModel.getNextPrice() + "");
                    holder.buttonPrice.setTag(R.string.key_memberID, productModel.getOwnerCode() + "");
                    holder.buttonPrice.setEnabled(true);
                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius_green);
                }

            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }

        });



    }


    private boolean checkItemOwner(String memberOwner) {
        if (PreferencesMange.getInstance().getMemberID().equals(memberOwner)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return productModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewNameProduct)
        TextView textViewNameProduct;
        @BindView(R.id.textViewTimeProduct)
        TextView textViewTimeProduct;
        @BindView(R.id.textViewType)
        TextView textViewType;
        @BindView(R.id.buttonPrice)
        Button buttonPrice;
        @BindView(R.id.imageViewProduct)
        ImageView imageViewProduct;
        @BindView(R.id.priceMarket)
        TextView priceMarket;
        @BindView(R.id.progress)
        ProgressBar progress;
        CountDownTimer countDownTimer;
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemProduct(ProductRealTimeModel productModel);

        void clickItemProductBuy(ProductRealTimeModel productModel, long price, String memberIdOwner);

        void clickItemFail(String error);
    }
}

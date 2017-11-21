package com.example.enter_01.vfin.component.buysell.allproduct;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductModel;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private ArrayList<ProductModel> productModel;
    private int stop = 0;
    private CallBackClick callBackClick;

    public ProductAdapter(ArrayList<ProductModel> productModel, CallBackClick callBackClick) {
        this.productModel = productModel;
        this.callBackClick = callBackClick;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_product, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textViewNameProduct.setText(productModel.get(position).getName());
        holder.textViewTimeProduct.setText(productModel.get(position).getTime() + " ..");
        holder.priceMarket.setText("ราคาตลาด\n"+productModel.get(position).getPrice_market());

        Glide.with(Contextor.getInstance().getContext()).load(productModel.get(position)
                .getImage_product().get(0))
                .into
                        (holder.imageViewProduct);


        if (holder.countDownTimer != null) {
            holder.countDownTimer.cancel();
        }

        holder.buttonPrice.setText( "รอคนซื้อ "+ productModel.get(position).getPrice() +
                " Vin point");
        holder.buttonPrice.setTag(R.string.key_price,productModel.get(position).getPrice()+"");
        holder.buttonPrice.setTag(R.string.key_memberID,"");

        long tsLong = System.currentTimeMillis() / 1000;

        final long time1 = productModel.get(position).getTime() - tsLong;

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
                Log.e("done!");
            }
        }.start();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackClick.clickItemProduct(productModel.get(position));
            }
        });

        holder.buttonPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                callBackClick.clickItemProductBuy(productModel.get(position), Long.parseLong(holder
                        .buttonPrice
                        .getTag(R.string.key_price)
                        .toString())
                        ,holder.buttonPrice.getTag(R.string.key_memberID).toString());
            }
        });
        final int[] price = {productModel.get(position).getPrice()};
        AllProductManage.getInstance().getProduct(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
              //  int price = 0;
                 int finalPrice = 0;
                try {

                    ProductModel productModel1 = (ProductModel) t;
                    if (productModel1.getMember_buy().get(productModel1.getMember_buy().size() - 1)
                            .getPrice() > 0)
                    {
                         price[0] = productModel1.getMember_buy().get(productModel1
                                .getMember_buy().size() - 1).getPrice();
                        double totel = (1 / 100.0f) * price[0];
                        price[0] = (int) (price[0] +Math.ceil(totel));
                        holder.buttonPrice.setText(price[0] + "");
                        holder.buttonPrice.setTag(R.string.key_price, price[0] +"");
                        holder.buttonPrice.setTag(R.string.key_memberID,productModel1.getMember_buy().get(productModel1
                                .getMember_buy().size() - 1).getMember_id());
                    }
                    finalPrice = price[0];
                    final int finalPrice1 = finalPrice;
                    AllProductManage.getInstance().getMemberOfProduct(new Query.CallBackData() {
                        @Override
                        public <T> void onSuccess(T t) {
                            Member member = (Member) t;
                            try {

                                CropCircleTransformation multi = new CropCircleTransformation();
                                Glide.with(Contextor.getInstance().getContext()).load(member.getImage_profile()).apply(bitmapTransform(multi)).into(holder.imageProfile);

                                if (member.getMember_id().equals(PreferencesMange.getInstance().getMemberID())) {
                                    holder.buttonPrice.setText("รอคนซื้อ "+ finalPrice1 +
                                            " Vin point");
                                    holder.buttonPrice.setTag(R.string.key_price,finalPrice1+"");
                                    holder.buttonPrice.setTag(R.string.key_memberID,member.getMember_id()+"");
                                    holder.buttonPrice.setEnabled(false);
                                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
                                } else {
                                    holder.buttonPrice.setText("ซื้อเลย "+ finalPrice1+
                                            " Vin point");
                                    holder.buttonPrice.setTag(R.string.key_price,finalPrice1+"");
                                    holder.buttonPrice.setTag(R.string.key_memberID,member.getMember_id()+"");

                                    //  holder.textViewType.setText("ซื้อต่อทันที");
                                    holder.buttonPrice.setEnabled(true);
                                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius_green);
                                }


                                holder.textViewUserName.setText(member.getName());
                            } catch (Exception e) {
                                holder.buttonPrice.setText("ซื้อเลย "+ finalPrice1+
                                        " Vin point");
                                holder.buttonPrice.setTag(R.string.key_price,finalPrice1+"");
                                holder.buttonPrice.setTag(R.string.key_memberID,member.getMember_id());

                                //   holder.textViewType.setText("ซื้อต่อทันที");
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
                    }, productModel1.getMember_buy()
                            .get(productModel1.getMember_buy().size() - 1).member_id);

                }catch (Exception e){
                    holder.buttonPrice.setText("ซื้อเลย "+ price[0] +
                            " Vin point");
                    holder.buttonPrice.setTag(price[0]+"");
                  //  holder.textViewType.setText("ซื้อต่อทันที");
                    holder.buttonPrice.setEnabled(true);
                    holder.buttonPrice.setBackgroundResource(R.drawable.button_radius_green);
                }


                //    Log.d("onSuccessonSuccessonSuccess",new Gson().toJson(member));
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        }, productModel.get(position).product_id);
    }

    @Override
    public int getItemCount() {
        return productModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.imageProfile)
        ImageView imageProfile;
        @BindView(R.id.textViewUserName)
        TextView textViewUserName;   @BindView(R.id.priceMarket)
        TextView priceMarket;
        CountDownTimer countDownTimer;
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemProduct(ProductModel productModel);

        void clickItemProductBuy(ProductModel productModel, long price,String memberIdOwner);
    }
}

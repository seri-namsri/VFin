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
import com.example.enter_01.vfin.component.buysell.allproduct.pojo.ProductRealTimeModel;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.Log;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    private ArrayList<ProductRealTimeModel> productModel;
    private CallBackClick callBackClick;

    public ProductAdapter(ArrayList<ProductRealTimeModel> productModel, CallBackClick callBackClick) {
        this.productModel = productModel;
        this.callBackClick = callBackClick;

    }


    public void addData(ArrayList<ProductRealTimeModel> productModel){
        this.productModel= productModel;
        notifyDataSetChanged();
    }

    public void dataChange(int position){
        notifyItemChanged(position);
    }

    public void removeData(int position){
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

      /*  AllProductManage allProductManage = new AllProductManage();
        allProductManage.getProductIdFormRealtime(holder.database,productModel.get
               (position).getId() + "", new Query .CallBackData() {
                    @Override
                    public <T> void onSuccess(T t) {
                        ProductRealTimeModel productRealTimeModel = (ProductRealTimeModel) t;
                        if (productRealTimeModel != null)
                            setDataOrtherItem(productRealTimeModel, holder);
                   /*     else{
                            try {
                                Log.d("AllProductManage",position +"   .");
                                productModel.remove(position);
                                notifyDataSetChanged();

                            }catch (Exception e){}
                        }*/
                 /*   }

                    @Override
                    public <T> void onSuccessAll(ArrayList<T> tArrayList) {

                    }

                    @Override
                    public void onFail(String error) {

                    }
                });*/

    }




    private void setDataOrtherItem(final ProductRealTimeModel productModel, final ViewHolder holder) {
        holder.textViewNameProduct.setText(productModel.getName());
        holder.textViewTimeProduct.setText(productModel.getExpiredTime() + " ..");
        holder.priceMarket.setText("ราคาตลาด\n" + productModel.getNextPrice());

        Glide.with(Contextor.getInstance().getContext()).load(productModel
                .getImgUrl())
                .into
                        (holder.imageViewProduct);


        if (holder.countDownTimer != null) {
            holder.countDownTimer.cancel();
        }

        holder.buttonPrice.setText("รอคนซื้อ " + productModel.getNextPrice() +
                " Vin point");
        holder.buttonPrice.setTag(R.string.key_price, productModel.getNextPrice() + "");
        holder.buttonPrice.setTag(R.string.key_memberID, "");

        long tsLong = System.currentTimeMillis();

        final long time1 = productModel.getExpiredTime() - tsLong;

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
                holder.textViewTimeProduct.setText("หมดเวลา");
                holder.buttonPrice.setText("หมดเวลา");
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

        holder.buttonPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callBackClick.clickItemProductBuy(productModel, Long.parseLong(holder
                                .buttonPrice
                                .getTag(R.string.key_price)
                                .toString())
                        , holder.buttonPrice.getTag(R.string.key_memberID).toString());
            }
        });
        if (PreferencesMange.getInstance().getMemberID().equals(productModel.ownerCode)){
            holder.buttonPrice.setText("รอคนซื้อ "+ productModel.getNextPrice() +
                    " Vin point");
            holder.buttonPrice.setTag(R.string.key_price,productModel.getNextPrice()+"");
            holder.buttonPrice.setTag(R.string.key_memberID,productModel.getOwnerCode()+"");
            holder.buttonPrice.setEnabled(false);
            holder.buttonPrice.setBackgroundResource(R.drawable.button_radius);
        } else {
            holder.buttonPrice.setText("ซื้อเลย "+ productModel.getNextPrice()+
                    " Vin point");
            holder.buttonPrice.setTag(R.string.key_price,productModel.getNextPrice()+"");
            holder.buttonPrice.setTag(R.string.key_memberID,productModel.getOwnerCode()+"");

            //  holder.textViewType.setText("ซื้อต่อทันที");
            holder.buttonPrice.setEnabled(true);
            holder.buttonPrice.setBackgroundResource(R.drawable.button_radius_green);
        }

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
        TextView textViewUserName;
        @BindView(R.id.priceMarket)
        TextView priceMarket;
        CountDownTimer countDownTimer;
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick {
        void clickItemProduct(ProductRealTimeModel productModel);

        void clickItemProductBuy(ProductRealTimeModel productModel, long price, String memberIdOwner);
    }
}

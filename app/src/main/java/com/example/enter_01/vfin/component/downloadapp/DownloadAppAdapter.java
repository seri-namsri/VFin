package com.example.enter_01.vfin.component.downloadapp;

import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.component.downloadapp.pojo.DownloadAppModel;
import com.example.enter_01.vfin.utility.Contextor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enter_01 on 11/9/2017 AD.
 */

public class DownloadAppAdapter extends  RecyclerView.Adapter<DownloadAppAdapter.ViewHolder>{

    private ArrayList<DownloadAppModel> appModels ;
    private CallBackClick callBackClick ;

    public DownloadAppAdapter(ArrayList<DownloadAppModel> appModels,CallBackClick callBackClick){
        this.appModels = appModels ;
        this.callBackClick = callBackClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout
                .adapter_download_app, parent, false));

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
         holder.textViewName.setText(appModels.get(position).getName());
         holder.textViewPoint.setText(appModels.get(position).getPoint() + " v");
         holder.textViewStatus.setText((appInstalledOrNot(appModels.get(position).getPaketname())
         ) ? "โหลดแล้ว":"ยังไม่โหลด");
        Glide.with(Contextor.getInstance().getContext()).load(appModels.get(position).getIcon_app())
                .into(holder.imageViewIconApp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackClick.clickItemMessage(appModels.get(position));
            }
        });
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = Contextor.getInstance().getContext().getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    @Override
    public int getItemCount() {
        return appModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewPoint) TextView textViewPoint;
        @BindView(R.id.textViewStatus) TextView textViewStatus;
        @BindView(R.id.imageViewIconApp)
        ImageView imageViewIconApp;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface CallBackClick{
        void clickItemMessage(DownloadAppModel downloadAppModel);
    }
}

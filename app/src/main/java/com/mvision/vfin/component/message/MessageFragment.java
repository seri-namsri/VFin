package com.mvision.vfin.component.message;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.component.messagedetail.MessageDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MessageFragment extends BaseFragment  implements MessageContract.View{

    @BindView(R.id.recyclerViewMessage)RecyclerView recyclerView;
    @BindView(R.id.progress)ProgressBar progressBar;
    private MessagePresenter presenter ;

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    protected void initializePresenter() {
        presenter = new MessagePresenter(this);
        super.presenter = presenter ;
    }

    @Override
    protected void startView() {
        presenter.getMessage();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_message;
    }


    public static String clickMessageData = "clickMessageData";
    @Override
    public  void setUpViewMessage(ArrayList<MessageData> message) {
        try {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            MessageAdapter messageAdapter = new MessageAdapter(message, new MessageAdapter.CallBackClick() {
                @Override
                public void clickItemMessage(MessageData messageData) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(clickMessageData, Parcels.wrap(messageData));
                    startActivityFromFragment(MessageDetailActivity.class,bundle);
                }
            });
            recyclerView.setAdapter(messageAdapter);
        }catch (Exception e){}


    }
}

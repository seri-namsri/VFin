package com.mvision.vfin.component.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.api.response.MessageResponseModel;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.component.messagedetail.MessageDetailActivity;
import com.mvision.vfin.utility.Log;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class MessageFragment extends BaseFragment implements MessageContract.View {

    @BindView(R.id.recyclerViewMessage)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    private MessagePresenter presenter;

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
        super.presenter = presenter;
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
    private LinearLayoutManager linearLayoutManager;
    private MessageAdapter messageAdapter;

    @Override
    public void setUpViewMessage(MessageResponseModel messageResponseModel) {
        try {
            linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addOnScrollListener(onScrollListener);
            messageAdapter = new MessageAdapter(messageResponseModel.result, new MessageAdapter.CallBackClick() {
                @Override
                public void clickItemMessage(MessageData messageData,int position) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(clickMessageData, Parcels.wrap(messageData));
                    bundle.putInt("position",position);
                    startActivityResultFromFragment(MessageDetailActivity.class, bundle,1);

                }
            });
            recyclerView.setAdapter(messageAdapter);
        } catch (Exception e) {
        }

    }

    @Override
    public void setUpViewMessageMore(MessageResponseModel messageResponseModel) {
        loading = true;
        messageAdapter.addMessage(messageResponseModel.result);
    }

    @Override
    public void showTextNotFound() {

    }

    @Override
    public void hideLoadingMore() {
        messageAdapter.checkLoading();
    }

    @Override
    public void getRemoveMessageSuccess(int position) {
        messageAdapter.notifyItemRemoved(position);
    }


    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading = true;


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dy > 0) //check for scroll down
            {
                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        presenter.getMessageMore();
                    }
                }
            }
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            presenter.getRemoveMessage(requestCode,data);
    }



}

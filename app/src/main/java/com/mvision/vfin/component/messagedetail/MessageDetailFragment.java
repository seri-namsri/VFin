package com.mvision.vfin.component.messagedetail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.message.pojo.MessageData;

import butterknife.BindView;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageDetailFragment extends BaseFragment implements MessageDetailContract.View {

    @BindView(R.id.textViewTitle)TextView textViewTitle;
    @BindView(R.id.textViewDetail)TextView textViewDetail;
    @BindView(R.id.buttonMyItem)Button buttonMyItem;
    @BindView(R.id.toolbar)Toolbar toolbar;

    private MessageDetailPresenter presenter ;

    public static MessageDetailFragment newInstance(Bundle bundle ) {
        MessageDetailFragment fragment = new MessageDetailFragment();
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
        presenter = new MessageDetailPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getMessageDetail();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_message_detail;
    }

    @Override
    public void setUpViewMessageDetail(MessageData messageDetail) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ข้อความ");
        textViewTitle.setText(messageDetail.getTitle());
        textViewDetail.setText(messageDetail.getDetail());
    }





}

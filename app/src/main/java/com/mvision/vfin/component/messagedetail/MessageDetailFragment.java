package com.mvision.vfin.component.messagedetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.financehistory.FinanceHistoryActivity;
import com.mvision.vfin.component.message.pojo.MessageData;
import com.mvision.vfin.error.ErrorMange;
import com.mvision.vfin.utility.ConvertDate;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enter_01 on 11/6/2017 AD.
 */

public class MessageDetailFragment extends BaseFragment implements MessageDetailContract.View {

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.textViewDetail)
    TextView textViewDetail;
    @BindView(R.id.textViewDate)
    TextView textViewDate;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MessageDetailPresenter presenter;

    public static MessageDetailFragment newInstance(Bundle bundle) {
        MessageDetailFragment fragment = new MessageDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {

    }

    @Override
    public void showLoading() {
        showProgress(getString(R.string.textLoading));
    }

    @Override
    public void hideLoading() {
        dismissProgress();
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
        textViewDetail.setText(messageDetail.getMessage());
        textViewDate.setText(ConvertDate.getInstance()
                .TimestampToFormatDateAndTimeTH(messageDetail.getCreatedDate() + "", "เวลาส่ง dd " +
                        "MMM yyyy HH:mm น."));
        button.setText(messageDetail.getOptions().btn.get(0).title);
    }

    @Override
    public void goToWalletHistory() {
        startActivityFromFragment(FinanceHistoryActivity.class, null);
    }

    @Override
    public void showDeleteMessageSuccess(int position) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @OnClick({R.id.button})
    public void clickButton(View view) {
        presenter.getOnClickButton();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_message, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.removeMessage:
                showAlertCustom(getString(R.string.textDeleteMessage), getString(R.string.textOk), getString(R.string
                                .textCancel),
                        true, new
                                ErrorMange.CallBackAlertClick() {
                                    @Override
                                    public void clickOk() {
                                        presenter.getDeleteMessage();
                                    }

                                    @Override
                                    public void clickCancel() {

                                    }
                                });
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

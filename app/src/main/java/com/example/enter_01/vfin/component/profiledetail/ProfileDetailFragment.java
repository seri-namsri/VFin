package com.example.enter_01.vfin.component.profiledetail;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;
import com.example.enter_01.vfin.component.profile.model.Member;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailFragment extends BaseFragment implements ProfileDetailContract.View {

    @BindView(R.id.textViewEmail)
    TextView textViewEmail;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewTel)
    TextView textViewTel;
    @BindView(R.id.textViewBirdDay)
    TextView textViewBirdDay;
    @BindView(R.id.textViewIdentiFication)
    TextView textViewIdentiFication;
    @BindView(R.id.textViewGender)
    TextView textViewGender;
    @BindView(R.id.imageViewProfile)
    ImageView imageViewProfile;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ProfileDetailPresenter presenter;

    public static ProfileDetailFragment newInstance() {
        ProfileDetailFragment fragment = new ProfileDetailFragment();
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
        presenter = new ProfileDetailPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getProfile();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_profile_detail;
    }

    @Override
    public void setUpViewProfile(Member member) {
        setUptoolBar();
        textViewEmail.setText(member.getEmail());
        textViewName.setText(member.getName());
        textViewTel.setText(member.getTel());
        textViewBirdDay.setText(member.getBirdday());
        textViewIdentiFication.setText(member.getIdentification());
        textViewGender.setText(member.getGender());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageViewProfile.setElevation(10);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        CropCircleTransformation multi = new CropCircleTransformation();
        Glide.with(this).load(member.getImage_profile()).apply(bitmapTransform(multi)).into(imageViewProfile);

        AdapterAddress adapterAddress = new AdapterAddress(member.getAddress());
        recyclerView.setAdapter(adapterAddress);
    }


    private void setUptoolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ข้อมูลส่วนตัว");
    }


    @OnClick({R.id.linearLayoutName, R.id.linearLayoutEmail, R.id.linearLayoutIdentiFication, R.id
            .linearLayoutBirdDay, R.id.linearLayoutPassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutName:
                break;
            case R.id.linearLayoutEmail:
                break;
            case R.id.linearLayoutIdentiFication:
                break;
            case R.id.linearLayoutBirdDay:
                break;
            case R.id.linearLayoutPassword:
                break;
        }
    }


}

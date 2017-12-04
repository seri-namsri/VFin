package com.mvision.vfin.component.profiledetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.addeditdress.AddEditAddressActivity;
import com.mvision.vfin.component.addeditdress.model.AddressModel;
import com.mvision.vfin.component.editprofile.EditProfileActivity;
import com.mvision.vfin.component.myaddress.MyAddressActivity;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.customview.TextViewWithFont;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.Utility;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by MVISION on 11/3/2017 AD.
 */

public class ProfileDetailFragment extends BaseFragment implements ProfileDetailContract.View {

    @BindView(R.id.textViewEmail)
    TextViewWithFont textViewEmail;
    @BindView(R.id.textViewAddress)
    TextViewWithFont textViewAddress;
    @BindView(R.id.textViewName)
    TextViewWithFont textViewName;
    @BindView(R.id.textViewTel)
    TextViewWithFont textViewTel;
    @BindView(R.id.textViewBirdDay)
    TextViewWithFont textViewBirdDay;
    @BindView(R.id.textViewIdentiFication)
    TextViewWithFont textViewIdentiFication;
    @BindView(R.id.imageViewProfile)
    ImageView imageViewProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ProfileDetailPresenter presenter;

    public static ProfileDetailFragment newInstance(Bundle bundle) {
        ProfileDetailFragment fragment = new ProfileDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showMessageFail(String msg) {
        Utility.ShowMsg(getActivity(),msg);

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
    public void setUpViewProfile(MemberResponseModel member) {
        try {
            setUptoolBar();
            textViewEmail.setText(member.result.email + "");
            textViewName.setText(member.result.firstName);
            textViewTel.setText(member.result.mobilePhoneNo);
            textViewBirdDay.setText(member.result.dateOfBirth);
            textViewIdentiFication.setText(member.result.personalId);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageViewProfile.setElevation(10);
            }
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.picture);
            requestOptions.error(R.drawable.picture);
            CropCircleTransformation multi = new CropCircleTransformation();
            Glide.with(this).load(member.result.avatarLink)
                    .apply(bitmapTransform(multi))
                    .apply(requestOptions)
                    .into(imageViewProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setUpViewAddress(AddressModel addressModel) {
        textViewAddress.setVisibility(View.VISIBLE);
        textViewAddress.setText(addressModel.getAddressAll());
    }

    @Override
    public void startEditProfile(Bundle bundle, int requestCode) {
        startActivityResultFromFragment(EditProfileActivity.class, bundle, requestCode);
    }

    @Override
    public void startAddAddress(int requestCode) {
        startActivityResultFromFragment(AddEditAddressActivity.class, null, requestCode);
    }

    @Override
    public void startChangeAddress(int requestCode) {
        startActivityResultFromFragment(MyAddressActivity.class, null, requestCode);
    }

    @Override
    public void startEditAddress(Bundle bundle,int requestCode) {
        startActivityResultFromFragment(AddEditAddressActivity.class, bundle, requestCode);
    }

    @Override
    public void finishFragment(MemberResponseModel member) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("memberResponseModel", Parcels.wrap(member));
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();

    }

    @Override
    public void showGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 7);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.changeData(requestCode, data);
    }


    private void setUptoolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ข้อมูลส่วนตัว");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getfinishFragment();
            }
        });
        getViewLayout().setFocusableInTouchMode(true);
        getViewLayout().requestFocus();
        getViewLayout().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        presenter.getfinishFragment();
                        return true;
                    }
                }
                return false;
            }
        });

    }


    @OnClick({R.id.linearLayoutName, R.id.linearLayoutEmail, R.id
            .linearLayoutBirdDay, R.id.linearLayoutPassword,R.id.imageViewProfile,R.id
            .buttonAddressAdd,R.id.buttonEditMainAddress,R.id.buttonChangeMainAddress})
    public void onClick(View view) {
        presenter.clickEditProfile(view.getId());

    }


}

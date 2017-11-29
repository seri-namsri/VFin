package com.mvision.vfin.component.profile;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.authen.login.LoginActivity;
import com.mvision.vfin.component.leveldetail.LevelDetailActivity;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.component.profile.model.ProfileMore;
import com.mvision.vfin.component.profiledetail.ProfileDetailActivity;
import com.mvision.vfin.customview.PercenView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class ProfileFragment extends BaseFragment implements ProfileContract.View {


    @BindView(R.id.imageViewProfile)
    ImageView imageViewProfile;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewEmail)
    TextView textViewEmail;
    @BindView(R.id.textViewProfilePercent)
    TextView textViewProfilePercent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.perCentView)
    PercenView perCentView;
    @BindView(R.id.buttonViewProfile)
    Button buttonViewProfile;
    ProfilePresenter presenter;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @OnClick({R.id.buttonViewProfile, R.id.buttonLevelDetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonViewProfile:
                presenter.clickProfile();
                break;
            case R.id.buttonLevelDetail:
                presenter.clickLevelDetail();
                break;
        }
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
        presenter = new ProfilePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        presenter.getProfile();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_profile;
    }

    @Override
    public void setUpViewProfile(MemberResponseModel member) {
        if (perCentView != null) {
            perCentView.setC("0.4");
            textViewName.setText(member.result.firstName);
            textViewEmail.setText(member.result.email);
            textViewProfilePercent.setText(member.result.memberLevel+"");
            imageViewProfile.bringToFront();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageViewProfile.setElevation(10);
                // buttonViewProfile.setElevation(40);
            }
            CropCircleTransformation multi = new CropCircleTransformation();

            Glide.with(this).load(member.result.avatarLink).apply(bitmapTransform(multi)).into
                    (imageViewProfile);

        }

    }

    @Override
    public void setUpViewMore(ArrayList<ProfileMore> arrayList) {
        ProfileMoreAdapter profileMoreAdapter = new ProfileMoreAdapter(arrayList, itemMore);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileMoreAdapter);
    }

    @Override
    public void OpenActivity(Class className, Bundle bundle) {
        startActivityFromFragment(className,bundle);
    }

    @Override
    public void logOutSuccess() {
        getActivity().finish();
        startActivityFromFragment(LoginActivity.class,null);
    }

    @Override
    public void startProfileDetail(Bundle bundle) {
        startActivityFromFragment(ProfileDetailActivity.class, bundle);
    }

    @Override
    public void startLevelDetail() {
        startActivityFromFragment(LevelDetailActivity.class, null);
    }

    private ProfileMoreAdapter.OnClickItemMore itemMore = new ProfileMoreAdapter.OnClickItemMore() {
        @Override
        public void itemClick(int position) {
            presenter.clickItemMore(position);
        }
    };
}

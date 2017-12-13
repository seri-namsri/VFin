package com.mvision.vfin.component.profile;

import android.content.Intent;
import android.os.Bundle;

import com.mvision.vfin.R;
import com.mvision.vfin.base.presenter.Presenter;
import com.mvision.vfin.component.financehistory.FinanceHistoryActivity;
import com.mvision.vfin.component.myproduct.myproductmain.MyProductMainActivity;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.component.profile.model.ProfileMore;
import com.mvision.vfin.firebase.Firestore.Query;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.PreferencesMange;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class ProfilePresenter extends Presenter<ProfileContract.View> implements ProfileContract.Presenter {

    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }


    private MemberResponseModel memberResponseModel;


    @Override
    public void getProfile() {
        view.setUpViewMore(setDateMore());
        ProfileManager.getInstance().getMemberApi(new Query.CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
                memberResponseModel = (MemberResponseModel) t;

                Log.e("ProfileDetailPresenter", new Gson().toJson(memberResponseModel));
                view.setUpViewProfile(memberResponseModel);
            }

            @Override
            public <T> void onSuccessAll(ArrayList<T> tArrayList) {

            }

            @Override
            public void onFail(String error) {

            }
        });


    }

    @Override
    public void clickItemMore(int position) {

        Class className = null;
        Bundle bundle = null;
        switch (position) {
            case 0:
                //  className = FinanceHistoryActivity.class;
                break;
            case 1:
                className = FinanceHistoryActivity.class;
                break;

            case 2:
                className = MyProductMainActivity.class;
                break;

            case 3:
                // className = FinanceHistoryActivity.class;
                break;

            case 4:
                // className = FinanceHistoryActivity.class;
                break;
            case 5:
                PreferencesMange.getInstance().removeMemberID();
                view.logOutSuccess();
                break;


        }
        if (className != null)
            view.OpenActivity(className, bundle);
    }

    @Override
    public void logOut() {

    }

    @Override
    public void clickProfile() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("profile", Parcels.wrap(memberResponseModel));
        view.startProfileDetail(bundle);
    }

    @Override
    public void clickLevelDetail() {
        view.startLevelDetail();
    }

    @Override
    public void changeData(Intent intent) {
        memberResponseModel = Parcels.unwrap(intent.getExtras().getParcelable
                ("memberResponseModel"));
        view.setUpViewProfile(memberResponseModel);
    }

    private ArrayList<ProfileMore> setDateMore() {
        ArrayList<ProfileMore> moreArrayList = new ArrayList<>();
        ProfileMore profileMore = new ProfileMore();
        profileMore.setName("ภารกิจ");
        profileMore.setRes_image(R.drawable.ic_treasure);
        moreArrayList.add(profileMore);

        profileMore = new ProfileMore();
        profileMore.setName("ประวัติคะแนน");
        profileMore.setRes_image(R.drawable.ic_financial_history);
        moreArrayList.add(profileMore);


        profileMore = new ProfileMore();
        profileMore.setName("สินค้าของฉัน");
        profileMore.setRes_image(R.drawable.ic_my_product);
        moreArrayList.add(profileMore);

        profileMore = new ProfileMore();
        profileMore.setName("คำถามที่พบบ่อย");
        profileMore.setRes_image(R.drawable.ic_faq);
        moreArrayList.add(profileMore);

        profileMore = new ProfileMore();
        profileMore.setName("ตั้งค่า");
        profileMore.setRes_image(R.drawable.ic_setting);
        moreArrayList.add(profileMore);

        profileMore = new ProfileMore();
        profileMore.setName("ออกจากระบบ");
        profileMore.setRes_image(R.drawable.ic_logout);
        moreArrayList.add(profileMore);


        return moreArrayList;
    }


}

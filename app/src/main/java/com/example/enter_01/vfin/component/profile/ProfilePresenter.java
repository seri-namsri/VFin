package com.example.enter_01.vfin.component.profile;

import android.os.Bundle;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.presenter.Presenter;
import com.example.enter_01.vfin.component.financehistory.FinanceHistoryActivity;
import com.example.enter_01.vfin.component.myproduct.MyProductActivity;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.component.profile.model.ProfileMore;
import com.example.enter_01.vfin.firebase.Firestore.Query;
import com.example.enter_01.vfin.utility.Contextor;
import com.example.enter_01.vfin.utility.PreferencesMange;
import com.example.enter_01.vfin.utility.Utility;

import java.util.ArrayList;

/**
 * Created by MVISION on 11/2/2017 AD.
 */

public class ProfilePresenter extends Presenter<ProfileContract.View> implements ProfileContract.Presenter {

    private ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void getProfile() {
        view.setUpViewMore(setDateMore());
        ProfileManager.getInstance().getMember(Utility.loadSavedPreferences(Contextor.getInstance
                ().getContext(), "member_id"), new Query
                .CallBackData() {
            @Override
            public <T> void onSuccess(T t) {
//                view.setUpViewProfile((Member) t);
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
                 className = MyProductActivity.class;
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

    private ArrayList<ProfileMore> setDateMore() {
        ArrayList<ProfileMore> moreArrayList = new ArrayList<>();
        ProfileMore profileMore = new ProfileMore();
        profileMore.setName("ภารกิจ");
        profileMore.setRes_image(R.drawable.ic_treasure);
        moreArrayList.add(profileMore);

        profileMore = new ProfileMore();
        profileMore.setName("ประวัติการเงิน");
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

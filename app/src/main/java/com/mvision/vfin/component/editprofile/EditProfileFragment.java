package com.mvision.vfin.component.editprofile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.customview.EditTextWithFont;
import com.mvision.vfin.utility.Utility;

import org.parceler.Parcels;


/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileContract.View{

    private EditProfilePresenter presenter ;

    public static EditProfileFragment newInstance(Bundle bundle) {
        EditProfileFragment fragment = new EditProfileFragment();
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
       presenter = new EditProfilePresenter(this);
       super.presenter = presenter;
    }

    @Override
    protected void startView() {
         presenter.getLayout();
    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return presenter.getLayoutEdit();
    }

    @Override
    public void setUpView(int layout) {

    }


    @Override
    public void initVariableChangeName() {
        final EditTextWithFont editTextName = getViewLayout().findViewById(R.id.editTextName);
        final EditTextWithFont  editTextSureName = getViewLayout().findViewById(R.id.editTextSureName);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar  toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar,"ชื่อ-นามสกุล");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  presenter.upDateName(editTextName.getTextDataNotNull(null),
                          editTextSureName.getText
                          ().toString());
            }
        });
    }

    private void setUptoolBar(Toolbar toolbar,String title) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    @Override
    public void initVariableChangePassword() {
        final EditTextWithFont  editTextPassOld = getViewLayout().findViewById(R.id.editTextPassOld);
        final EditTextWithFont  editTextPassNew = getViewLayout().findViewById(R.id.editTextPassNew);
        final EditTextWithFont  editTextPassNewConfirm = getViewLayout().findViewById(R.id.editTextPassNewConfirm);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar  toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar,"รหัสผ่าน");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDatePassWord(editTextPassOld.getTextDataNotNull(null),editTextPassNew
                        .getTextDataNotNull(null),editTextPassNewConfirm.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void initVariableChangeEmail() {
        final EditTextWithFont  editTextEmail = getViewLayout().findViewById(R.id.editTextEmail);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar  toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar,"อีเมล");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDateEmail(editTextEmail.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void initVariableChangeBirthDay() {
        final EditTextWithFont  editTextBirthDay = getViewLayout().findViewById(R.id.editTextBirtDay);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar  toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar,"วันเกิด");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 presenter.upDateBirthDay(editTextBirthDay.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void upDateSuccess(MemberUpdate memberUpdate) {
        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("memberUpdate", Parcels.wrap(memberUpdate));
        returnIntent.putExtras(bundle);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }


}

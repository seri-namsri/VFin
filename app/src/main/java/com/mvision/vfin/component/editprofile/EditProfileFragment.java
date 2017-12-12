package com.mvision.vfin.component.editprofile;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mvision.vfin.R;
import com.mvision.vfin.api.modelrequest.MemberUpdate;
import com.mvision.vfin.base.BaseFragment;
import com.mvision.vfin.component.profile.model.MemberResponseModel;
import com.mvision.vfin.customview.EditTextWithFont;
import com.mvision.vfin.customview.TextViewWithFont;
import com.mvision.vfin.utility.Log;
import com.mvision.vfin.utility.Utility;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by enter_01 on 11/29/2017 AD.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileContract.View, DatePickerDialog.OnDateSetListener {

    private EditProfilePresenter presenter;

    public static EditProfileFragment newInstance(Bundle bundle) {
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void showMessageFail(String msg) {
        Utility.ShowMsg(getActivity(), msg);
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
    public void initVariableChangeName(MemberResponseModel memberResponseModel) {
        final EditTextWithFont editTextName = getViewLayout().findViewById(R.id.editTextName);
        final EditTextWithFont editTextSureName = getViewLayout().findViewById(R.id.editTextSureName);
        editTextName.setText(memberResponseModel.result.firstName);
        editTextSureName.setText(memberResponseModel.result.lastName);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar, "ชื่อ-นามสกุล");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDateName(editTextName.getTextDataNotNull(null),
                        editTextSureName.getText
                                ().toString());
            }
        });
    }



    private void setUptoolBar(Toolbar toolbar, String title) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        applyFontForToolbarTitle(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }


    @Override
    public void initVariableChangePassword() {
        final EditTextWithFont editTextPassOld = getViewLayout().findViewById(R.id.editTextPassOld);
        final EditTextWithFont editTextPassNew = getViewLayout().findViewById(R.id.editTextPassNew);
        final EditTextWithFont editTextPassNewConfirm = getViewLayout().findViewById(R.id.editTextPassNewConfirm);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar, "รหัสผ่าน");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDatePassWord(editTextPassOld.getTextDataNotNull(null), editTextPassNew
                        .getTextDataNotNull(null), editTextPassNewConfirm.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void initVariableChangeEmail(MemberResponseModel memberResponseModel) {
        final EditTextWithFont editTextEmail = getViewLayout().findViewById(R.id.editTextEmail);
        editTextEmail.setText(memberResponseModel.result.email);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar, "อีเมล");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDateEmail(editTextEmail.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void initVariableChangeBirthDay(MemberResponseModel memberResponseModel) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final TextViewWithFont editTextBirthDay = getViewLayout().findViewById(R.id.editTextBirtDay);
        editTextBirthDay.setText(memberResponseModel.result.dateOfBirth);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(), EditProfileFragment.this, 2017, 02, 01);
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                Calendar newDate = Calendar.getInstance();

                DatePicker datePicker = datePickerDialog.getDatePicker();
                newDate.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

                editTextBirthDay.setText(dateFormatter.format(newDate.getTime()));

            }

        });
        editTextBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
        setUptoolBar(toolbar, "วันเกิด");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDateBirthDay(editTextBirthDay.getTextDataNotNull(null));
            }
        });
    }

    @Override
    public void initVariableChangeGender(MemberResponseModel memberResponseModel) {
        final RadioButton  radioButtonMen = getViewLayout().findViewById(R.id.radioButtonMen);
        final RadioButton  radioButtonFemale = getViewLayout().findViewById(R.id.radioButtonFemale);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar, "เพศ");
        final String[] gender = {""};
        if (memberResponseModel.result.gender.equals("female")){
            radioButtonFemale.setChecked(true);
        }else if (memberResponseModel.result.gender.equals("male")){
            radioButtonMen.setChecked(true);

        }
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButtonMen.isChecked()){
                    gender[0] = "male";
                }else if (radioButtonFemale.isChecked()){
                    gender[0] = "female";
                }
                presenter.upDateGender(gender[0]);
            }
        });
    }

    @Override
    public void initVariableChangePersonal() {
        final EditTextWithFont editTextPersonalId = getViewLayout().findViewById(R.id.editTextPersonalId);
        Button buttonOk = getViewLayout().findViewById(R.id.buttonOk);
        Toolbar toolbar = getViewLayout().findViewById(R.id.toolbar);
        setUptoolBar(toolbar, "เลขบัตรประจำตัวประชาชน");
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.upDatePersonal(editTextPersonalId.getTextDataNotNull(null));
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


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
    }
}

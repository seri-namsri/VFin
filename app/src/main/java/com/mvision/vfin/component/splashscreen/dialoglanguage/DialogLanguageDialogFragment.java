package com.mvision.vfin.component.splashscreen.dialoglanguage;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mvision.vfin.R;
import com.mvision.vfin.base.BaseDialogFragment;
import com.mvision.vfin.component.splashscreen.SplashScreenFragment;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by enter_01 on 12/17/2017 AD.
 */

public class DialogLanguageDialogFragment extends BaseDialogFragment implements
        DialogLanguageContract.View {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.radioButtonThai)
    RadioButton radioButtonThai;
    @BindView(R.id.radioButtonEnglish)
    RadioButton radioButtonEnglish;
    private DialogLanguagePresenter presenter;
    private static SplashScreenFragment.CallBackLanguage callBackLanguageDialog;

    public static DialogLanguageDialogFragment newInstance(SplashScreenFragment.CallBackLanguage callBackLanguage) {
        callBackLanguageDialog = callBackLanguage;
        DialogLanguageDialogFragment fragment = new DialogLanguageDialogFragment();
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
        presenter = new DialogLanguagePresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.dialog_fragment_select_language;
    }


    @OnClick({R.id.buttonOK})
    public void onClick(View view) {
        String lang = "";

        if (radioButtonThai.isChecked()) {
            lang = "th";
        } else if (radioButtonEnglish.isChecked()) {
            lang = "en";
        }


        presenter.selectLanguage(lang);
    }


    @Override
    public void selectSuccess() {
        callBackLanguageDialog.onClickSuccess();
        dismiss();
    }
}

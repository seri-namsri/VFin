package com.example.enter_01.vfin.component.question;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseFragment;

/**
 * Created by enter_01 on 11/8/2017 AD.
 */

public class QuestionFragment extends BaseFragment {

    public static  QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
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

    }

    @Override
    protected void startView() {

    }

    @Override
    protected int setLayoutResourceIdentifier() {
        return R.layout.fragment_question;
    }
}
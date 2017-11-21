package com.example.enter_01.vfin.component.main;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.enter_01.vfin.R;
import com.example.enter_01.vfin.base.BaseActivity;
import com.example.enter_01.vfin.component.activity.PageActivityFragment;
import com.example.enter_01.vfin.component.authen.login.LoginActivity;
import com.example.enter_01.vfin.component.buysell.mainproduct.BuySellFragment;
import com.example.enter_01.vfin.component.message.MessageFragment;
import com.example.enter_01.vfin.component.profile.ProfileFragment;
import com.example.enter_01.vfin.component.profile.model.Member;
import com.example.enter_01.vfin.component.reward.RewardFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements MainContract.View{

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.buttonCoin)
    Button buttonCoin;
    @BindView(R.id.buttonEnergy)
    Button buttonEnergy;
    MainPresenter presenter ;
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
    protected int setLayoutResourceIdentifier() {
        return R.layout.activity_main;
    }



    @Override
    protected void initializePresenter() {
        presenter = new MainPresenter(this);
        super.presenter = presenter;
    }

    @Override
    protected void startView() {
        initTab();
        presenter.showView();
    }


    private void initTab() {
        setupTabIcons();
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                Runtime.getRuntime().gc();
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = ProfileFragment.newInstance();
                        break;
                    case 1:
                        selectedFragment = MessageFragment.newInstance();
                        break;
                    case 2:
                        selectedFragment = BuySellFragment.newInstance();
                        break;
                    case 3:
                        selectedFragment = PageActivityFragment.newInstance();
                        break;
                    case 4:
                        selectedFragment = RewardFragment.newInstance();
                        break;
                }

                try {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, selectedFragment);
                    transaction.commit();
                } catch (NullPointerException e) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, ProfileFragment.newInstance());
        transaction.commit();
    }


    private void setupTabIcons() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.getTabAt(0).setIcon(R.drawable.click_menu_profile).setText(getText(R.string.tab_profile));
        tabLayout.getTabAt(1).setIcon(R.drawable.click_menu_message).setText(getText(R.string.tab_message));
        tabLayout.getTabAt(2).setIcon(R.drawable.click_menu_buy_sell).setText(getText(R.string.tab_buysell));
        tabLayout.getTabAt(3).setIcon(R.drawable.click_menu_activity).setText(getText(R.string.tab_activity));
        tabLayout.getTabAt(4).setIcon(R.drawable.click_menu_reward).setText(getText(R.string.tab_reward));
        changeTabsFont(tabLayout);

    }

    public void changeTabsFont(TabLayout layoutWithFont) {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Kanit-Regular.otf");
        ViewGroup vg = (ViewGroup) layoutWithFont.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
    }


    @Override
    public void setUpView(Member member) {
        buttonEnergy.setText(member.getEnergy()+"/99");
        buttonCoin.setText(member.getCoin()+"");
    }
}

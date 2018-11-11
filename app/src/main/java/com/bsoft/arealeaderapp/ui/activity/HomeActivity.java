package com.bsoft.arealeaderapp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.ui.base.BaseActivity;
import com.bsoft.arealeaderapp.ui.fragment.Right1Fragment;
import com.bsoft.arealeaderapp.ui.fragment.Right2Fragment;
import com.bsoft.arealeaderapp.util.SpUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        super.initView();
        String username = (String) SpUtil.get(this, "username", "");
        String password = (String) SpUtil.get(this, "password", "");
        Logger.i("username: " + username + ", password: " + password);

        replaceFragment(new Right1Fragment());
    }

    @OnClick(R.id.btn_item1)
    void item1ClickListener() {
        replaceFragment(new Right1Fragment());
    }

    @OnClick(R.id.btn_item2)
    void item2ClickListener() {
        replaceFragment(new Right2Fragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.commit();
    }
}

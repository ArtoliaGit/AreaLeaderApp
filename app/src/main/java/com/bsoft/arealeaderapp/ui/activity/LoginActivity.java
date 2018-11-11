package com.bsoft.arealeaderapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.ui.base.BaseActivity;
import com.bsoft.arealeaderapp.util.SpUtil;
import com.bsoft.arealeaderapp.util.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Artolia Pendragon
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.til_username)
    TextInputLayout til_username;

    @BindView(R.id.til_password)
    TextInputLayout til_password;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.btn_login)
    void loginListener() {
        String username = til_username.getEditText().getText().toString();
        String password = til_password.getEditText().getText().toString();

        if (validatePassword(username, password)) {
            til_username.setErrorEnabled(false);
            til_password.setErrorEnabled(false);
            SpUtil.put(LoginActivity.this, "username", username);
            SpUtil.put(LoginActivity.this, "password", password);
            ToastUtil.show(LoginActivity.this, "登录成功");
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            til_password.setErrorEnabled(true);
            til_password.setError("密码不正确");
        }
    }

    private boolean validatePassword(String username, String password) {
        Logger.i("username: " + username + ", password: " + password);
        return true;
    }
}

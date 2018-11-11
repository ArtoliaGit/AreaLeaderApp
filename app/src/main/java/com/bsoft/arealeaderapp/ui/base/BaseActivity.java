package com.bsoft.arealeaderapp.ui.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.util.ActivityPageManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;

/**
 * @author artolia
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //添加activity到活动管理器中
        ActivityPageManager.getInstance().addActivity(this);

        // 设置横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.i("日志插件初始化");

        initArgs();
        initView();
        initListener();
    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     * @return 布局id
     */
    protected abstract int provideContentViewId();

    public void initArgs() {
    }

    public void initView() {
    }

    public void initListener() {
    }

    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.basic_help);
        builder.setMessage(R.string.basic_string_help_text);

        builder.setNegativeButton(R.string.basic_quit, (dialog, which) -> {
            dialog.dismiss();
        });

        builder.setNegativeButton(R.string.basic_settings, (dialog, which) -> {
            startAppSettings();
        });
    }

    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }
}

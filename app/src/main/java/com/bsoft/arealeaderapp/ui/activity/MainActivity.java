package com.bsoft.arealeaderapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.widget.Button;
import android.widget.TextView;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.api.UserService;
import com.bsoft.arealeaderapp.common.CustomApiResult;
import com.bsoft.arealeaderapp.common.SystemParameter;
import com.bsoft.arealeaderapp.common.UpdateCallback;
import com.bsoft.arealeaderapp.common.toolbar.NormalTitleBar;
import com.bsoft.arealeaderapp.common.toolbar.ScrollTitleBar;
import com.bsoft.arealeaderapp.entity.User;
import com.bsoft.arealeaderapp.ui.base.BaseActivity;
import com.bsoft.arealeaderapp.util.HttpManager;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.lzh.framework.updatepluginlib.UpdateBuilder;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author artolia
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView textView;

    @BindView(R.id.container)
    CoordinatorLayout mContainer;

    RxPermissions rxPermissions;
    Disposable disposable;
    UpdateCallback updateCallback;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initArgs() {
        super.initArgs();
        rxPermissions = new RxPermissions(this);
        updateCallback = new UpdateCallback(MainActivity.this);
        getPermissions();
    }

    @Override
    public void initView() {
        super.initView();
        new ScrollTitleBar.Builder(this, mContainer)
                .setTitle("朝阳创卫")
                .setLeftIcon(R.drawable.ic_back)
                .build();
    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @OnClick(R.id.btn)
    void mButtonListener() {
        HttpManager.get("user/getUserInfo")
                .execute(new SimpleCallBack<User>() {

                    @Override
                    public void onError(ApiException e) {
                        Logger.i("请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onSuccess(User user) {
                        Logger.i("请求成功：" + user.toString());
                    }
                });
    }

    @OnClick(R.id.btn2)
    void retrofitListener() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SystemParameter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService userService = retrofit.create(UserService.class);
        Call<CustomApiResult<User>> call = userService.getUser();
        call.enqueue(new Callback<CustomApiResult<User>>() {
            @Override
            public void onResponse(Call call, Response response) {
                Logger.i(response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Logger.i("请求失败");
            }
        });
    }

    @OnClick(R.id.btn_login)
    void LoginListener() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_webview)
    void webviewClickListener() {
        Intent intent = new Intent(MainActivity.this, WebviewActivity.class);
        intent.putExtra("url", "http://www.baidu.com");
        startActivity(intent);
    }

    private void getPermissions() {
        disposable = rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Logger.i("获取权限成功");
                        createBuilder().check();
                    } else {
                        Logger.i("获取权限失败");
                        showMissingPermissionDialog();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @NonNull
    private UpdateBuilder createBuilder() {
        UpdateBuilder builder = UpdateBuilder.create();
        builder.setDownloadCallback(updateCallback);
        builder.setCheckCallback(updateCallback);
        return builder;
    }

}

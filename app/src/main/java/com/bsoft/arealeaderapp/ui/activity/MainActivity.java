package com.bsoft.arealeaderapp.ui.activity;

import android.Manifest;
import android.widget.Button;
import android.widget.TextView;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.ui.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

/**
 * @author artolia
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button mButton;

    @BindView(R.id.tv)
    TextView textView;

    RxPermissions rxPermissions;
    Disposable disposable;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        rxPermissions = new RxPermissions(this);
        getPermissions();
        mButton.setOnClickListener(v -> {
            textView.setText("");
            EasyHttp.get("")
                    .execute(new SimpleCallBack<Object>() {
                        @Override
                        public void onError(ApiException e) {
                            Logger.i("网络请求失败");
                        }

                        @Override
                        public void onSuccess(Object o) {
                            Logger.i("网络请求成功");
                            Logger.i(String.valueOf(o));
                            textView.setText(String.valueOf(o));
                        }
                    });
        });
    }

    public void getPermissions() {
        disposable = rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        Logger.i("获取权限成功");
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
}

package com.bsoft.arealeaderapp.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.bsoft.arealeaderapp.R;
import com.bsoft.arealeaderapp.common.toolbar.ScrollTitleBar;
import com.bsoft.arealeaderapp.ui.base.BaseWebviewActivity;

import butterknife.BindView;

public class WebviewActivity extends BaseWebviewActivity {

    @BindView(R.id.fl_webview)
    FrameLayout webview;

    @BindView(R.id.container)
    CoordinatorLayout mContainer;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_webview;
    }

    @Override
    public void initView() {
        super.initView();
        new ScrollTitleBar.Builder(this, mContainer)
                .setTitle("朝阳创卫")
                .setLeftIcon(R.drawable.ic_back)
                .build();
    }

    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return webview;
    }

    @Nullable
    @Override
    protected String getUrl() {
        Intent intent = getIntent();
        return intent.getStringExtra("url");
    }

    @Nullable
    @Override
    protected WebViewClient getWebViewClient() {
        return new WebViewClient() {

        };
    }

    @Nullable
    @Override
    protected WebChromeClient getWebChromeClient() {
        return new WebChromeClient() {

        };
    }
}

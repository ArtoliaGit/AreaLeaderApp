package com.bsoft.arealeaderapp;

import android.app.Application;
import android.content.Context;

import com.bsoft.arealeaderapp.util.ActivityPageManager;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;

/**
 * @author artolia
 */
public class MyApplication extends Application {

    private static Application app = null;

    private static final String URL = "http://www.baidu.com";

    @Override
    public void onCreate() {
        super.onCreate();

        initEasyHttp();
    }

    private void initEasyHttp() {
        //默认初始化,必须调用
        EasyHttp.init(this);

        EasyHttp.getInstance()
                .setBaseUrl(URL)
                .debug("EasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)
                .setRetryDelay(500)
                .setRetryIncreaseDelay(500);

    }

    private void initUpdate() {

    }

    public static Context getAppContext() {
        if (app == null) {
            return null;
        }
        return app.getApplicationContext();
    }

    /**
     * 退出程序
     */
    public static void exit() {
        ActivityPageManager.getInstance().finishAllActivity();
    }
}

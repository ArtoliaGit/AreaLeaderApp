package com.bsoft.arealeaderapp;

import android.app.Application;
import android.content.Context;

import com.bsoft.arealeaderapp.common.SystemParameter;
import com.bsoft.arealeaderapp.util.ActivityPageManager;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.model.HttpHeaders;

import org.json.JSONObject;
import org.lzh.framework.updatepluginlib.UpdateConfig;
import org.lzh.framework.updatepluginlib.base.UpdateParser;
import org.lzh.framework.updatepluginlib.base.UpdateStrategy;
import org.lzh.framework.updatepluginlib.model.CheckEntity;
import org.lzh.framework.updatepluginlib.model.Update;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author artolia
 */
public class MyApplication extends Application {

    private static Application app = null;

    @Override
    public void onCreate() {
        super.onCreate();

        initEasyHttp();
        initUpdate();
    }

    private void initEasyHttp() {
        //默认初始化,必须调用
        EasyHttp.init(this);

        EasyHttp.getInstance()
                .setBaseUrl(SystemParameter.BASE_URL)
                .debug("EasyHttp", true)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)
                .setRetryDelay(500)
                .setRetryIncreaseDelay(500);
    }

    private void initUpdate() {
        UpdateConfig.getConfig()
                .setCheckEntity(new CheckEntity().setUrl(SystemParameter.CHECK_UPDATE_URL))
                .setUpdateParser(new UpdateParser() {
                    @Override
                    public Update parse(String response) throws Exception {
                        JSONObject object = new JSONObject(response);
                        Update update = new Update();
                        // apk下载地址
                        update.setUpdateUrl(object.optString("update_url"));
                        // apk的版本号
                        update.setVersionCode(object.optInt("update_ver_code"));
                        // apk的版本名称
                        update.setVersionName(object.optString("update_ver_name"));
                        // apk的更新内容
                        update.setUpdateContent(object.optString("update_content"));
                        // 是否强制更新
                        update.setForced(object.optBoolean("update_forced", false));
                        // 是否忽略此次版本更新
                        update.setIgnore(object.optBoolean("ignore_able", false));
                        return update;
                    }
                })
                .setUpdateStrategy(new UpdateStrategy() {
                    @Override
                    public boolean isShowUpdateDialog(Update update) {
                        return true;
                    }

                    @Override
                    public boolean isShowDownloadDialog() {
                        return true;
                    }

                    @Override
                    public boolean isAutoInstall() {
                        return true;
                    }
                });
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

package com.bsoft.arealeaderapp.common;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.lzh.framework.updatepluginlib.base.CheckCallback;
import org.lzh.framework.updatepluginlib.base.DownloadCallback;
import org.lzh.framework.updatepluginlib.model.Update;

import java.io.File;

/**
 * @author Artolia Pendragon
 */
public class UpdateCallback implements CheckCallback, DownloadCallback {

    private Context mContext;

    public UpdateCallback(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCheckStart() {
        Logger.i("启动更新任务");
    }

    @Override
    public void hasUpdate(Update update) {
        Logger.i("检测到有更新");
    }

    @Override
    public void noUpdate() {
        Logger.i("检测到没有更新");
    }

    @Override
    public void onCheckError(Throwable t) {
        Logger.i("更新检测失败：" + t.getMessage());
    }

    @Override
    public void onUserCancel() {
        Logger.i("用户取消更新");
    }

    @Override
    public void onCheckIgnore(Update update) {
        Logger.i("用户忽略此版本更新");
    }

    @Override
    public void onDownloadStart() {
        Logger.i("开始下载");
    }

    @Override
    public void onDownloadComplete(File file) {
        Logger.i("下载完成");
    }

    @Override
    public void onDownloadProgress(long current, long total) {

    }

    @Override
    public void onDownloadError(Throwable t) {
        Logger.i("下载失败：" + t.getMessage());
    }
}

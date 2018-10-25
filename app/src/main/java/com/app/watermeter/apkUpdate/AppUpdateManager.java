package com.app.watermeter.apkUpdate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.alibaba.sdk.android.push.common.util.JSONUtils;
import com.app.okhttputils.Model.Result;
import com.app.watermeter.common.ApplicationHolder;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.eventBus.ApkInfoEvent;
import com.app.watermeter.model.ApkInfoModel;
import com.app.watermeter.model.NetVersionModel;
import com.app.watermeter.model.UserInfoModel;
import com.app.watermeter.model.VersionData;
import com.app.watermeter.model.VersionModel;
import com.app.watermeter.okhttp.DataManager;
import com.app.watermeter.okhttp.MyOkhttpUtils;
import com.app.okhttputils.callback.GenericsCallback;
import com.app.okhttputils.request.JsonGenericsSerializator;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hasee on 2018/2/5.
 */

public class AppUpdateManager {

    public static AppUpdateManager instance = null;
    private static DataManager dataInstance = null;

    private AppUpdateManager() {

    }

    public static AppUpdateManager getInstance() {
        if (instance == null) {
            instance = new AppUpdateManager();
        }
        if (dataInstance == null) {
            dataInstance = DataManager.getInstance();
        }
        return instance;
    }

    public long getContentLength(String url) {
        if (url == null) {
            return 0;
        }
        OkHttpClient okhttpClient = MyOkhttpUtils.getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = okhttpClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.body().close();
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void getApkVersionInfo(final boolean isSelfCheck) {
        dataInstance.sendGetRequestData(CommonUrl.GET_APK_VERSION_INFO, null)
                .execute(new GenericsCallback<Result>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Response response, Call call, Exception e, int i) {

                        EventBus.getDefault().post(new ApkInfoEvent(null, isSelfCheck));
                    }

                    @Override
                    public void onNetWorkError(Response response, String errorMsg, int NetWorkCode) {
                        EventBus.getDefault().post(new ApkInfoEvent(null, isSelfCheck));

                    }

                    @Override
                    public void onResponse(Result result, int i) {
                        Log.d("admin", "onResponse: result="+result);
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(result.getData());
                        Log.d("admin", "onResponse: jsonString="+jsonString);
                        VersionData apkInfoModel = gson.fromJson(jsonString, VersionData.class);
                        Log.d("admin", "onResponse: apkInfoModel=" + apkInfoModel);
                        EventBus.getDefault().post(new ApkInfoEvent(apkInfoModel, isSelfCheck));
                    }

                });

    }

    public boolean needUpdateApk(VersionData apkInfoModel) {
        if (apkInfoModel == null) {
            return false;
        }
        String apkVersion = apkInfoModel.getVersion();// 更新版本

        VersionModel versionModel = ApplicationHolder.getInstance().getVersionModel();
        if (versionModel == null || apkVersion == null) {
            return false;
        }
        String versionName = versionModel.getVersionName();// 当前安装版本

        if (!apkVersion.equals(versionName)) {
            return true;
        }
        return false;
    }

    public void updateNewApk(Context context, VersionData apkInfoModel) {
        String apkPath = apkInfoModel.getUrl();
        if (apkPath == null) {
            return;
        }
        DownLoadApkFile loadApkFile = new DownLoadApkFile();
        loadApkFile.updateAPk(context, apkPath);
    }

    public void installApk(Context appContext, String filePath) {
        //Context appContext = ApplicationHolder.getAppContext();
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // ".fileprovider"即是在清单文件中配置的authorities
            data = FileProvider.getUriForFile(appContext, "com.app.watermeter.fileProvider", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }

        intent.setDataAndType(data, "application/vnd.android.package-archive");
        appContext.startActivity(intent);
    }
}

package com.app.watermeter.common;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.LocaleList;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.register.GcmRegister;
import com.alibaba.sdk.android.push.register.HuaWeiRegister;
import com.alibaba.sdk.android.push.register.MiPushRegister;
import com.app.watermeter.utils.LanguageUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.app.watermeter.R;
import com.app.watermeter.okhttp.MyOkhttpUtils;
import com.app.watermeter.utils.PickViewUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * Created by hasee on 2018/8/8.
 */

public class ComApplication extends Application {
    private static final String TAG = "Init";

    public static ComApplication mzjApplication;
    public List<Activity> mActivityList = null;

    public static int currentLanguage;

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
    /**
     * 在这里做一些全局的初始化操作
     */
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationHolder.getInstance().setAppContext(this);
        mzjApplication = this;
//         CrashManager.getInstance().init(this); //初始化本地崩溃日志收集
        MyOkhttpUtils.initOkhttp(this);
        PickViewUtil.initTimePickOptions(this);
        LanguageUtils.applyChange(this);
        ZXingLibrary.initDisplayOpinion(this);
        initCloudChannel(this);
    }
    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private void initCloudChannel(final Context applicationContext) {
        // 创建notificaiton channel
        this.createNotificationChannel();

        PushServiceFactory.init(applicationContext);
        final CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG, "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e(TAG, "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });

        MiPushRegister.register(applicationContext, "XIAOMI_ID", "XIAOMI_KEY"); // 初始化小米辅助推送
        HuaWeiRegister.register(applicationContext); // 接入华为辅助推送
        GcmRegister.register(applicationContext, "send_id", "application_id"); // 接入FCM/GCM初始化推送
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = "1";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    /**
     * 获取application实例
     *
     * @return
     */
    public static ComApplication getApp() {
        return mzjApplication;
    }





    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (mActivityList == null) {
            mActivityList = new CopyOnWriteArrayList<>();
        }

        if (activity != null) {

            mActivityList.add(activity);
        }
    }

    /**
     * 退出栈中所有的activity，用于退出登录
     */
    public void removeAllActivity() {
        if (mActivityList != null) {
            for (Activity activity : mActivityList) {
                removeActivity(activity);
                activity.finish();
            }
        }
    }

    /**
     * 退出栈中所有的activity，用于退出登录
     */
    public void removeAllActivity(Activity currentActivity) {
        if (mActivityList != null) {
            for (Activity activity : mActivityList) {
                if (activity == currentActivity) {
                    return;
                }
                removeActivity(activity);
                activity.finish();
            }
        }
    }

    /**
     * 获取当前的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        if (mActivityList != null) {
            if (mActivityList.size() > 0) {
                return mActivityList.get(mActivityList.size() - 1);
            }
        }
        return null;
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList != null && activity != null) {
            mActivityList.remove(activity);
        }
    }
}

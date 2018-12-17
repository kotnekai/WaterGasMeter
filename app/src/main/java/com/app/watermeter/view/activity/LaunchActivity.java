package com.app.watermeter.view.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.view.base.BaseActivity;

public class LaunchActivity extends BaseActivity {

    @Override
    protected int getCenterView() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initHeader() {
        setHeaderVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPage();
    }

    private void startPage() {
        String token = PreferencesUtils.getString(CommonParams.USER_TOKEN);
        if (token == null) {
            startActivity(LoginActivity.makeIntent(this));
        } else {

           String phoneNumber =  PreferencesUtils.getString(CommonParams.USER_ACCOUNT);

            //绑定推送
            initPushBinding(phoneNumber);

            startActivity(MainActivity.makeIntent(this));
        }
        finish();
    }

    /**
     * 绑定推送
     * @param phone
     */
    private void initPushBinding(String phone) {

        PushServiceFactory.getCloudPushService().bindAccount(phone, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                System.out.print("====bind account " + "" + " success\n");
                MeterManager.getInstance().deviceUpload(PushServiceFactory.getCloudPushService().getDeviceId());
            }

            @Override
            public void onFailed(String errorCode, String errorMsg) {
                System.out.print("===bind account " + "" + " failed." +
                        "errorCode: " + errorCode + ", errorMsg:" + errorMsg);
            }
        });
    }


}

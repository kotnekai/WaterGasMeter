package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.apkUpdate.AppUpdateManager;
import com.app.watermeter.common.ApplicationHolder;
import com.app.watermeter.eventBus.ApkInfoEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.ApkInfoModel;
import com.app.watermeter.model.NetVersionModel;
import com.app.watermeter.model.VersionData;
import com.app.watermeter.model.VersionModel;
import com.app.watermeter.utils.DateUtils;
import com.app.watermeter.utils.DialogUtils;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class VersionActivity extends BaseActivity {

    @BindView(R.id.tvVersionCode)
    TextView tvVersionCode;
    @BindView(R.id.tvCheckVersion)
    TextView tvCheckVersion;

    @BindView(R.id.llVersionLayout)
    LinearLayout llVersionLayout;

    @BindView(R.id.tvUpdateTime)
    TextView tvUpdateTime;

    @Override
    protected int getCenterView() {
        return R.layout.activity_version;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, VersionActivity.class);
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(UIUtils.getValueString(R.string.act_ver_title));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        VersionModel versionModel = ApplicationHolder.getInstance().getVersionModel();

        tvVersionCode.setText(versionModel.getVersionName());
        tvUpdateTime.setText(UIUtils.getValueString(R.string.act_ver_update_time) + DateUtils.getFormatSystemTime(versionModel.getLastUpdateTime()));
        tvCheckVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressUtils.getIntance().setProgressDialog(UIUtils.getValueString(R.string.act_ver_update_checking), VersionActivity.this);
                AppUpdateManager.getInstance().getApkVersionInfo(true);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onApkInfoEvent(ApkInfoEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        final VersionData apkInfoModel = event.getApkInfoModel();
        boolean selfCheck = event.isSelfCheck();
        if (!selfCheck) {
            return;
        }
        if (apkInfoModel == null) {
            ToastUtil.showShort(UIUtils.getValueString(R.string.request_data_error));
            return;
        }
        boolean needUpdateApk = AppUpdateManager.getInstance().needUpdateApk(apkInfoModel);
        if (needUpdateApk) {
            AppUpdateManager.getInstance().updateNewApk(VersionActivity.this, apkInfoModel);

        } else {
            ToastUtil.showShort(UIUtils.getValueString(R.string.act_is_new_ver));
        }
    }
}

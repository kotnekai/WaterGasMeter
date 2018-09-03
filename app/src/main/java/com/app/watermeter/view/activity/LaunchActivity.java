package com.app.watermeter.view.activity;

import android.os.Bundle;
import android.view.View;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
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
            startActivity(MainActivity.makeIntent(this));
        }
        finish();
    }
}

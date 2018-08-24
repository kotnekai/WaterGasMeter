package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.llModifyPwd)
    LinearLayout llModifyPwd;
    @BindView(R.id.llChangedLanguage)
    RelativeLayout llChangedLanguage;

    private Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_setting;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingActivity.class);
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.mine_other_setting));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SettingActivity.this;

    }

    @OnClick({R.id.llModifyPwd, R.id.llChangedLanguage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llModifyPwd:
                startActivity(ResetPswActivity.makeIntent(mContext, ResetPswActivity.TYPE_MODIFY));
                break;
            case R.id.llChangedLanguage:
                break;


        }
    }

}

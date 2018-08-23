package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterInfoActivity extends BaseActivity {

    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtEmails)
    EditText edtEmails;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPsw)
    EditText edtConfirmPsw;
    @BindView(R.id.TvSubmitInfo)
    TextView TvSubmitInfo;

    @Override
    protected int getCenterView() {
        return R.layout.activity_register_info;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, RegisterInfoActivity.class);
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_info_title));
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {

    }
}

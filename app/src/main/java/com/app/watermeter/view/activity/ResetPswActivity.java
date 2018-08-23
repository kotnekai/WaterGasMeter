package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ResetPswActivity extends BaseActivity {
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPsw)
    EditText edtConfirmPsw;
    @BindView(R.id.TvSubmitInfo)
    TextView TvSubmitInfo;

    @Override
    protected int getCenterView() {
        return R.layout.activity_reset_psw;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ResetPswActivity.class);
    }
    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.reset_psw_title));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {

    }
}

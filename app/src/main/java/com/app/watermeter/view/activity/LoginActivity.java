package com.app.watermeter.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.llSerialNumber)
    LinearLayout llSerialNumber;
    @BindView(R.id.tvSelectSerial)
    TextView tvSelectSerial;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.tvLoginBtn)
    TextView tvLoginBtn;
    @BindView(R.id.tvGoRegister)
    TextView tvGoRegister;
    @BindView(R.id.tvForgetPsw)
    TextView tvForgetPsw;

    @Override
    protected int getCenterView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initHeader() {
        setHeaderVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.llSerialNumber, R.id.tvLoginBtn, R.id.tvGoRegister, R.id.tvForgetPsw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llSerialNumber:
                break;
            case R.id.tvLoginBtn:
                startActivity(MainActivity.makeIntent(this));
                finish();
                break;
            case R.id.tvGoRegister:
                startActivity(RegisterPhoneActivity.makeIntent(this));
                break;
            case R.id.tvForgetPsw:
                break;

        }
    }
}

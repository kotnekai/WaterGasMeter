package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCodeActivity extends BaseActivity {

    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.edtCodeFirst)
    EditText edtCodeFirst;
    @BindView(R.id.edtCodeSecond)
    EditText edtCodeSecond;
    @BindView(R.id.edtCodeThird)
    EditText edtCodeThird;
    @BindView(R.id.edtCodeFourth)
    EditText edtCodeFourth;
    @BindView(R.id.tvGoNext)
    TextView tvGoNext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_register_code;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, RegisterCodeActivity.class);
    }

    @Override
    protected void initHeader() {

    }

    @OnClick({R.id.tvGoNext})
    public void getCode() {
        startActivity(RegisterInfoActivity.makeIntent(this));
    }

}

package com.app.watermeter.view.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.utils.AccountValidatorUtil;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterPhoneActivity extends BaseActivity {

    @BindView(R.id.rlSelectCode)
    RelativeLayout rlSelectCode;
    @BindView(R.id.edtPhoneNumber)
    EditText edtPhoneNumber;
    @BindView(R.id.tvGoNext)
    TextView tvGoNext;
    @BindView(R.id.tvCountryCode)
    TextView tvCountryCode;

    private String countryCode;
    private String phoneNumber;

    @Override
    protected int getCenterView() {
        return R.layout.activity_register_phone;
    }


    public static Intent makeIntent(Context context) {
        return new Intent(context, RegisterPhoneActivity.class);
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_phone_title));
    }

    @OnClick({R.id.rlSelectCode, R.id.tvGoNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlSelectCode:

                break;
            case R.id.tvGoNext:
                phoneNumber = edtPhoneNumber.getText().toString();
                countryCode = tvCountryCode.getText().toString();
//                boolean mobile = AccountValidatorUtil.isMobile(phoneNumber);
//                if (!mobile || EmptyUtil.isEmpty(countryCode)) {
//                    ToastUtil.showShort(getString(R.string.phone_number));
//                    return;
//                }
                startActivity(RegisterCodeActivity.makeIntent(this, countryCode, phoneNumber));

                break;
        }
    }



}

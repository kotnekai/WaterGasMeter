package com.app.watermeter.view.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.okhttputils.Model.Result;
import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.UserCache;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.SpinnerSelectModel;
import com.app.watermeter.utils.AccountValidatorUtil;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.InitUtils;
import com.app.watermeter.utils.PickViewUtil;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jaaksi.pickerview.dataset.OptionDataSet;
import org.jaaksi.pickerview.picker.OptionPicker;

import java.util.List;

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
    @BindView(R.id.tvCountryName)
    TextView tvCountryName;
    private int fromType;

    @Override
    protected int getCenterView() {
        return R.layout.activity_register_phone;
    }


    public static Intent makeIntent(Context context, int fromType) {
        Intent intent = new Intent(context, RegisterPhoneActivity.class);
        intent.putExtra(CommonParams.fromType, fromType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        fromType = intent.getIntExtra(CommonParams.fromType, 0);

    }

    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_phone_title));
    }

    @OnClick({R.id.rlSelectCode, R.id.tvGoNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlSelectCode:
                List<SpinnerSelectModel> cityCodeList = InitUtils.getCityCodeList();

                PickViewUtil.showSelectPickDialog(this, cityCodeList, 1, new OptionPicker.OnOptionSelectListener() {
                    @Override
                    public void onOptionSelect(OptionPicker picker, int[] selectedPosition, OptionDataSet[] selectedOptions) {
                        SpinnerSelectModel selectedOption = (SpinnerSelectModel) selectedOptions[0];
                        if (selectedOption.getName().equals("86")) {
                            tvCountryName.setVisibility(View.VISIBLE);
                        } else {
                            tvCountryName.setVisibility(View.INVISIBLE);
                        }
                        tvCountryCode.setText(selectedOption.getName());
                    }
                });
                break;
            case R.id.tvGoNext:
                showLoadingDialog();
                String phoneNumber = edtPhoneNumber.getText().toString();
                String countryCode = tvCountryCode.getText().toString();
//                boolean mobile = AccountValidatorUtil.isMobile(phoneNumber);
                if (EmptyUtil.isEmpty(phoneNumber)) {
                    ToastUtil.showShort(getString(R.string.phone_number));
                    return;
                }
                if (fromType == 1) {
                    UserManager.getInstance().sendSmsToCheck(countryCode + phoneNumber, CommonParams.BUSS_REGISTER_TYPE);
                } else {
                    UserManager.getInstance().sendSmsToCheck(countryCode + phoneNumber, CommonParams.BUSS_RESET_TYPE);
                }

                break;
        }
    }

    /**
     * 发送验证码结果接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        dismissLoadingDialog();
        ProgressUtils.getIntance().dismissProgress();
        Result result = event.getResult();
        int status_code = result.getStatus_code();
        String message = result.getMessage();
        int errCode = result.getErr_code();//业务码
        ToastUtil.showShort(message);
        if (status_code == 200 && errCode == 0) {
            String phoneNumber = edtPhoneNumber.getText().toString();
            String countryCode = tvCountryCode.getText().toString();
            UserCache.getInstance().setPhoneNumber(phoneNumber);
            startActivityForResult(RegisterCodeActivity.makeIntent(this, countryCode, phoneNumber, fromType),CommonParams.FINISH_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CommonParams.FINISH_CODE)
        {
            if (resultCode==CommonParams.FINISH_CODE && fromType==2) {
                //重置密码成功，要finish
                setResult(CommonParams.FINISH_CODE);
                finish();
            }
        }
    }

}

package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.eventBus.CheckSmsCodeEvent;
import com.app.watermeter.eventBus.SuccessEvent;
import com.app.watermeter.manager.UserManager;
import com.app.watermeter.model.ComResponseModel;
import com.app.watermeter.utils.EmptyUtil;
import com.app.watermeter.utils.ProgressUtils;
import com.app.watermeter.utils.ToastUtil;
import com.app.watermeter.view.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterCodeActivity extends BaseActivity implements TextWatcher {
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
    @BindView(R.id.llVeryCode)
    LinearLayout llVeryCode;
    private final int millisInFuture = 60000;
    private final int countDownInterval = 1000;
    private CountTimer countTimer;
    private StringBuffer veryCode;
    private String countryCode;
    private String phoneNumber;


    @Override
    protected int getCenterView() {
        return R.layout.activity_register_code;
    }

    public static Intent makeIntent(Context context, String countryCode, String phoneNumber) {
        Intent intent = new Intent(context, RegisterCodeActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("countryCode", countryCode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            countryCode = intent.getStringExtra("countryCode");
            phoneNumber = intent.getStringExtra("phoneNumber");
            String formatNumber = String.format(getString(R.string.register_verify_phone), countryCode, phoneNumber);
            tvPhone.setText(formatNumber);
        }
        if (countTimer == null) {
            countTimer = new CountTimer(millisInFuture, countDownInterval);
            countTimer.start();///开启倒计时
        }
        edtCodeFirst.addTextChangedListener(this);
        edtCodeSecond.addTextChangedListener(this);
        edtCodeThird.addTextChangedListener(this);
        edtCodeFourth.addTextChangedListener(this);

        UserManager.getInstance().sendSmsToCheck(phoneNumber, CommonParams.BUSS_REGISTER_TYPE);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s == null || s.length() == 0) {
            return;
        }
        getCodeNumber();
    }

    /**
     * 拿到填入的验证码
     */
    private void getCodeNumber() {
        veryCode = new StringBuffer(llVeryCode.getChildCount());
        if (llVeryCode == null) {
            return;
        }
        for (int i = 0; i < llVeryCode.getChildCount(); i++) {
            String number = ((EditText) llVeryCode.getChildAt(i)).getText().toString();
            veryCode.append(number);
        }
        ToastUtil.showShort(veryCode);
        if (veryCode.length() != 4) {
            return;
        }
        ProgressUtils.getIntance().setProgressDialog(getString(R.string.com_loading_tips),this);
        UserManager.getInstance().checkSmsCode(phoneNumber, CommonParams.BUSS_REGISTER_TYPE,veryCode.toString());
    }

    /**
     * 发送验证码结果接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(SuccessEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        ComResponseModel successModel = event.getSuccessModel();
        int status_code = successModel.getStatus_code();
        String message = successModel.getMessage();
        int errCode = successModel.getErr_code();//业务码
        ToastUtil.showShort(message);
    }

    /**
     * 校验验证码结果接口返回
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckSmsCodeEvent(CheckSmsCodeEvent event) {
        ProgressUtils.getIntance().dismissProgress();
        ComResponseModel successModel = event.getSuccessModel();
        int status_code = successModel.getStatus_code();
        String message = successModel.getMessage();
        int errCode = successModel.getErr_code();//业务码
        ToastUtil.showShort(message);
        if (status_code == 200&&errCode==0) {
            startActivity(RegisterInfoActivity.makeIntent(this,phoneNumber));
        }

    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.register_verify_code));
    }

    @OnClick({R.id.tvGoNext})
    public void getCode() {
        startActivity(RegisterInfoActivity.makeIntent(this,phoneNumber));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countTimer != null) {
            countTimer.cancel();
        }
    }


    /**
     * 点击按钮后倒计时
     */
    class CountTimer extends CountDownTimer {

        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 倒计时过程中调用
         *
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            tvGoNext.setText(String.format(getString(R.string.register_count_time), (millisUntilFinished / 1000) + ""));
            tvGoNext.setEnabled(false);
        }

        /**
         * 倒计时完成后调用
         */
        @Override
        public void onFinish() {
            tvGoNext.setEnabled(true);
            tvGoNext.setText(getString(R.string.register_next_step));
        }
    }
}

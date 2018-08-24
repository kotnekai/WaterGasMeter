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

/**
 * @author admin
 */
public class ResetPswActivity extends BaseActivity {

    public final static String Pwd_TYPE = "pwdType";

    public final static int TYPE_MODIFY = 101;
    public final static int TYPE_RESET = 102;

    private int type ;

    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.edtConfirmPsw)
    EditText edtConfirmPsw;
    @BindView(R.id.TvSubmitInfo)
    TextView TvSubmitInfo;

    Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_reset_psw;
    }

    public static Intent makeIntent(Context context,int type) {
        Intent intent = new Intent(context, ResetPswActivity.class);
        intent.putExtra(Pwd_TYPE,type);
        return intent;
    }
    @Override
    protected void initHeader() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = ResetPswActivity.this;
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Pwd_TYPE, 0);
        if (type == TYPE_MODIFY) {
            setHeaderTitle(getString(R.string.reset_modify_title));
        } else if (type == TYPE_RESET) {
            setHeaderTitle(getString(R.string.reset_psw_title));
        }
    }

    @OnClick({R.id.TvSubmitInfo})
    public void submitUserInfo() {

    }
}

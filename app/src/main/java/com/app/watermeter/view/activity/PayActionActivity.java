package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author admin
 */
public class PayActionActivity extends BaseActivity {


    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.llPayStart)
    LinearLayout llPayStart;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.llPayEnd)
    LinearLayout llPayEnd;
    @BindView(R.id.tvPayAction)
    TextView tvPayAction;
    private Context mContext;

    @Override
    protected int getCenterView() {
        return R.layout.activity_pay_action;
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PayActionActivity.class);
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.payment_money_title));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PayActionActivity.this;

    }

    @OnClick({R.id.tvPayAction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPayAction:
                llPayStart.setVisibility(View.GONE);
                llPayEnd.setVisibility(View.VISIBLE);
                tvPayAction.setText(getString(R.string.completed));
                break;
        }
    }
}

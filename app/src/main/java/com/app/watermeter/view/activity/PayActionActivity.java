package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.eventBus.GetChartReadListEvent;
import com.app.watermeter.eventBus.GetPayResultEvent;
import com.app.watermeter.eventBus.GetPerPayEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.utils.DataUtils;
import com.app.watermeter.utils.DateUtils;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.base.WebViewActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private int meterId;

    @Override
    protected int getCenterView() {
        return R.layout.activity_pay_action;
    }

    public static Intent makeIntent(Context context, int meterId) {
        Intent intent = new Intent(context, PayActionActivity.class);
        intent.putExtra(CommonParams.METER_ID, meterId);
        return intent;
    }


    @Override
    protected void initHeader() {
        setHeaderTitle(getString(R.string.payment_money_title));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = PayActionActivity.this;
        getIntentData();
    }

    /**
     * 获取类型
     */
    private void getIntentData() {
        Intent intent = getIntent();
        meterId = intent.getIntExtra(CommonParams.METER_ID, 0);
    }

    @OnClick({R.id.tvPayAction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPayAction:
//                llPayStart.setVisibility(View.GONE);
//                llPayEnd.setVisibility(View.VISIBLE);
//                tvPayAction.setText(getString(R.string.completed));

                String money = etMoney.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {

                } else {
                    long payMoney = Long.valueOf(money);
                    MeterManager.getInstance().saveMoney(meterId, payMoney, CommonParams.USD);
                }
                break;
        }
    }

    /**
     * 接口返回--充值
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetPerPayEvent event) {
        if (event.getModelInfo() != null) {
            String tradeId = event.getModelInfo().getTrade_id() + "";

            String callTime = DateUtils.getGMT7PayTime();

            String security = DataUtils.getRandomStr() + CommonParams.SECURITY_KEY + tradeId + DataUtils.getRandomStr();

            String md5Str = DataUtils.MD5(security);
            MeterManager.getInstance().paymentAction(event.getModelInfo().getTrade_id(), callTime,md5Str);
        }
    }
    /**
     * 接口返回--支付
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetPayResultEvent event) {

        if(!TextUtils.isEmpty(event.getHtmlStr()))
        {
            String html = event.getHtmlStr();
           String htmlStr =  html.replace("./web/", CommonUrl.BASE_PAY_URL);

            mContext.startActivity(WebViewActivity.makeIntent(mContext, htmlStr));
        }
    }
}

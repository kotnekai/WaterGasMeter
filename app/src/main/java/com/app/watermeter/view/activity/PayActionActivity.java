package com.app.watermeter.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.CommonUrl;
import com.app.watermeter.common.Constants;
import com.app.watermeter.eventBus.GetPayResultEvent;
import com.app.watermeter.eventBus.GetPerPayEvent;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.utils.DataUtils;
import com.app.watermeter.utils.DateUtils;
import com.app.watermeter.utils.PreferencesUtils;
import com.app.watermeter.utils.ZxingUtils;
import com.app.watermeter.view.base.BaseActivity;
import com.app.watermeter.view.base.WebViewActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    @BindView(R.id.tvSn)
    TextView tvSn;
    @BindView(R.id.ivQR)
    ImageView ivQR;

    @BindView(R.id.QRBalance)
    TextView QRBalance;
    @BindView(R.id.QRId)
    TextView QRId;
    @BindView(R.id.QRRoomNumber)
    TextView QRRoomNumber;


    private Context mContext;
    private int meterId;
    private boolean fromScen = false;
    private String meterSn;
    private MeterInfoModel meterInfoModel;

    @Override
    protected int getCenterView() {
        return R.layout.activity_pay_action;
    }

    public static Intent makeIntent(Context context, int meterId, boolean isFromScan, String meterSn, MeterInfoModel meterInfo) {
        Intent intent = new Intent(context, PayActionActivity.class);
        intent.putExtra(CommonParams.METER_ID, meterId);
        intent.putExtra(CommonParams.FROM_SCAN, isFromScan);
        intent.putExtra(CommonParams.METER_SN, meterSn);
        intent.putExtra(CommonParams.METER_INFO, meterInfo);
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
        createQRImage();
    }


    /**
     * 获取类型
     */
    private void getIntentData() {
        Intent intent = getIntent();
        meterId = intent.getIntExtra(CommonParams.METER_ID, 0);
        fromScen = intent.getBooleanExtra(CommonParams.FROM_SCAN, false);
        meterSn = intent.getStringExtra(CommonParams.METER_SN);
        if (fromScen) {
            tvSn.setVisibility(View.VISIBLE);
            tvSn.setText(String.format(getString(R.string.scan_meter_sn), meterSn));
        } else {
            tvSn.setVisibility(View.GONE);
        }
        meterInfoModel = (MeterInfoModel) intent.getSerializableExtra(CommonParams.METER_INFO);

        if (meterInfoModel != null) {
            switch (ComApplication.currentLanguage) {
                case Constants.LANGUAGE_CHINA:
                    QRRoomNumber.setText(String.format(getString(R.string.recharge_room), meterInfoModel.getPosition_zh()));
                    break;
                case Constants.LANGUAGE_ENGLISH:
                    QRRoomNumber.setText(String.format(getString(R.string.recharge_room), meterInfoModel.getPosition_en()));
                    break;
                case Constants.LANGUAGE_KH:
                    QRRoomNumber.setText(String.format(getString(R.string.recharge_room), meterInfoModel.getPosition_kh()));
                    break;
                default:
                    QRRoomNumber.setText(String.format(getString(R.string.recharge_room), meterInfoModel.getPosition_zh()));
            }
            QRBalance.setText(String.format(getString(R.string.recharge_balance), meterInfoModel.getBalance()+""));
            QRId.setText(String.format(getString(R.string.recharge_id), meterSn));
        }
    }


    /**
     * 生成二维码
     */
    private void createQRImage() {
        ivQR.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final File outputPic = makeTempFile(mContext, Environment.getExternalStorageDirectory().getPath() + "/SHR AMR/QRImage/", "qr_", ".jpg");

//                        boolean success = ZxingUtils.createQRImage("SHRAMR-waterSn-" + meterSn, ivQR.getWidth(), ivQR.getWidth(), null, outputPic.getAbsolutePath());
                        boolean success = ZxingUtils.createQRImage(meterSn, ivQR.getWidth(), ivQR.getWidth(), null, outputPic.getAbsolutePath());

                        if (success) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ivQR.setImageBitmap(BitmapFactory.decodeFile(outputPic.getAbsolutePath()));
                                }
                            });
                        }
                    }
                }, 500);

            }
        });


    }

    @SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
    public static File makeTempFile(@NonNull Context context, @Nullable String saveDir, String prefix, String extension) {
        if (saveDir == null)
            saveDir = context.getExternalCacheDir().getAbsolutePath();
        final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        final File dir = new File(saveDir);
        dir.mkdirs();
        return new File(dir, prefix + timeStamp + extension);
    }


    @OnClick({R.id.tvPayAction})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvPayAction:
//                llPayStart.setVisibility(View.GONE);
//                llPayEnd.setVisibility(View.VISIBLE);
//                tvPayAction.setText(getString(R.string.completed));


                String money = etMoney.getText().toString().trim();
                if (!TextUtils.isEmpty(money)) {
                    try {
                        float payMoney = Float.valueOf(money);

                        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                        String payValue = decimalFormat.format(payMoney);
                        if (fromScen) {
                            MeterManager.getInstance().saveMoney(meterId, Float.valueOf(payValue), CommonParams.USD, CommonParams.ACTION_FROM_SCAN);
                        } else {
                            MeterManager.getInstance().saveMoney(meterId, Float.valueOf(payValue), CommonParams.USD, CommonParams.ACTION_FROM_DIRECT);
                        }
                    } catch (Exception ei) {
                        ei.printStackTrace();
                    }

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
            String orderNo = event.getModelInfo().getOut_trade_no();
            long timestamp = event.getModelInfo().getTimestamp();
            PreferencesUtils.putString("orderNo", orderNo);

//            String callTime = DateUtils.getGMT7PayTime();
            String callTime = DateUtils.getTimeStr(String.valueOf(timestamp));

            String security = DataUtils.getRandomStr() + DataUtils.MD5(CommonParams.PARTNER + CommonParams.SECURITY_KEY + tradeId + callTime) + DataUtils.getRandomStr();

            String html = "<!DOCTYPE><html><head><title>ACE API</title><meta charset=\"UTF-8\"/></head><body><form action=\"https://api.asiaweiluy.com/gateway.php?method=ace.trade.pay\" method=\"post\" id=\"awl_post\" target=\"_self\"><input type=\"hidden\" name=\"partner\" value=\"" + CommonParams.PARTNER + "\"/><input type=\"hidden\" name=\"trade_id\" value=\"" + tradeId + "\"/><input type=\"hidden\" name=\"call_time\" value=\"" + callTime + "\"/><input type=\"hidden\" name=\"security\" value=\"" + security.toLowerCase() + "\"/></form></body><script>window.onload=function(){document.getElementById(\"awl_post\").submit();}</script></html>\n";
            startActivityForResult(WebViewActivity.makeIntent(mContext, html), CommonParams.PAY_RESULT);
//            MeterManager.getInstance().paymentAction(event.getModelInfo().getTrade_id(), callTime, security.toLowerCase());
        }
    }

    /**
     * 接口返回--支付
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GetPayResultEvent event) {

        if (!TextUtils.isEmpty(event.getHtmlStr())) {
            String html = event.getHtmlStr();
            String htmlStr = html.replace("./web/", CommonUrl.BASE_PAY_URL);

            mContext.startActivity(WebViewActivity.makeIntent(mContext, htmlStr));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CommonParams.PAY_RESULT) {
            setResult(CommonParams.PAY_RESULT);
            finish();
        }
    }
}

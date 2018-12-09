package com.app.watermeter.view.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.ComApplication;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.common.Constants;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.view.activity.MeterDetailActivity;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author admin
 * @date 2018/8/23
 */

public class WaterPagerAdapter extends PagerAdapter {

    private List<MeterInfoModel> list;
    private Context context;

    public WaterPagerAdapter(Context context, List<MeterInfoModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_water_meter, null);

        TextView tvMeterName = (TextView)view.findViewById(R.id.tvMeterName);
        TextView  tvOpen = (TextView)view.findViewById(R.id.tvOpen);

        TextView tvMeterAddress = (TextView)view.findViewById(R.id.tvMeterAddress);
        TextView tvSquare = (TextView)view.findViewById(R.id.tvSquare);
        TextView  tvUnit = (TextView)view.findViewById(R.id.tvUnit);
        TextView  tvLastValue = (TextView)view.findViewById(R.id.tvLastValue);
        TextView  tvBalanceValue = (TextView)view.findViewById(R.id.tvBalanceValue);

        final MeterInfoModel info = list.get(position);
        if (info != null) {
            switch (ComApplication.currentLanguage) {
                case Constants.LANGUAGE_CHINA:
                    tvMeterAddress.setText(info.getLocation_zh()+info.getPosition_zh());
                    break;
                case Constants.LANGUAGE_ENGLISH:
                    tvMeterAddress.setText(info.getLocation_en()+info.getPosition_en());
                    break;
                case Constants.LANGUAGE_KH:
                    tvMeterAddress.setText(info.getLocation_kh()+info.getPosition_kh());
                    break;
                default:
                    tvMeterAddress.setText(info.getLocation_zh()+info.getPosition_zh());
            }

            tvMeterName.setText(info.getMachine_sn());
            if (info.getIs_opened()==0)
            {
                tvOpen.setText(context.getString(R.string.meter_close));
            }
            else
            {
                tvOpen.setText(context.getString(R.string.meter_open));
            }
            tvSquare.setText(info.getDegree()+"");
            tvUnit.setText(String.format(context.getString(R.string.square),info.getUnit()+""));
            tvLastValue.setText(info.getOld_degree()+"");
            tvBalanceValue.setText(info.getBalance()+"");
        }
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(MeterDetailActivity.makeIntent(context, CommonParams.TYPE_WATER, info.getMachine_sn()));
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

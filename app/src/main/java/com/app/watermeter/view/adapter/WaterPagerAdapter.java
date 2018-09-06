package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.view.activity.MeterDetailActivity;
import com.app.watermeter.view.activity.MeterListActivity;

import java.util.List;

/**
 *
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
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(MeterDetailActivity.makeIntent(context, CommonParams.TYPE_WATER));
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

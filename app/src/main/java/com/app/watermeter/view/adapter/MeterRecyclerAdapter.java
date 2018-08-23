package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.utils.UIUtils;
import com.app.watermeter.view.activity.MeterListActivity;

import java.util.List;


public class MeterRecyclerAdapter extends RecyclerView.Adapter<MeterRecyclerAdapter.MyViewHolder> {

    private View mHeaderView;
    private List<MeterInfoModel> mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private int type;

    public MeterRecyclerAdapter(Context context, List<MeterInfoModel> datas,int type) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
        this.type = type;
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MeterInfoModel model = mDatas.get(getRealPosition(holder));
//        holder.tvNewsTitle.setText(model.getTitle());
//        holder.tvNewsDate.setText(model.getDate());
//        holder.tvNewsDesc.setText(model.getDesc());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (type) {
            case MeterListActivity.TYPE_WATER:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
            case MeterListActivity.TYPE_ELECT:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
            case MeterListActivity.TYPE_GAS:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
            default:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
        }

        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {



        public MyViewHolder(View view) {
            super(view);

        }

    }
}
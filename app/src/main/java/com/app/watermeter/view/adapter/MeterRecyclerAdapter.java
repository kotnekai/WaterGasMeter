package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterInfoModel;
import com.app.watermeter.view.activity.MeterDetailActivity;
import com.app.watermeter.view.activity.MeterListActivity;

import java.util.List;


/**
 * @author admin
 */
public class MeterRecyclerAdapter extends RecyclerView.Adapter<MeterRecyclerAdapter.MyViewHolder> {

    private View mHeaderView;
    private List<MeterInfoModel> mDatas;
    private Context context;
    private LayoutInflater inflater;
    private int type;

    public MeterRecyclerAdapter(Context context, List<MeterInfoModel> datas, int type) {
        this.context = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(this.context);
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(MeterDetailActivity.makeIntent(context, type));
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        switch (type) {
            case CommonParams.TYPE_WATER:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
            case CommonParams.TYPE_ELECT:
                view = inflater.inflate(R.layout.item_meter_list, parent, false);
                break;
            case CommonParams.TYPE_GAS:
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

        CardView cardView;
        TextView tvMeterTitle, tvMeterAddress, tvSquare, tvLastValue, tvBalanceValue;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardView);
            tvMeterTitle = view.findViewById(R.id.tvMeterTitle);
            tvMeterAddress = view.findViewById(R.id.tvMeterAddress);
            tvSquare = view.findViewById(R.id.tvSquare);
            tvLastValue = view.findViewById(R.id.tvLastValue);
            tvBalanceValue = view.findViewById(R.id.tvBalanceValue);


        }

    }
}
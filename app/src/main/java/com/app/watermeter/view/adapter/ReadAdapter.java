package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.model.MeterReChargeModel;
import com.app.watermeter.model.MeterReadModel;

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 */
public class ReadAdapter extends Adapter<ReadAdapter.MyViewHolder> {
    private List<MeterReadModel> readList;
    private Context context;
    private LayoutInflater inflater;

    public ReadAdapter(Context context, List<MeterReadModel> readList) {
        this.context = context;
        this.readList = readList;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<MeterReadModel> list) {
        if (list != null && list.size() > 0)
            this.readList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_pre_save, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReadModel readModel = readList.get(position);
        if (readModel == null) {
            return;
        }
        holder.tvWaterSn.setText(String.format(context.getString(R.string.water_sn),readModel.getMachine_type_id()+""));
        holder.tvSaveMeasure.setText(String.format(context.getString(R.string.measurement),readModel.getRead_degree()+""));
        holder.tvSaveDate.setText(String.format(context.getString(R.string.payment_time),readModel.getCreated_at()));
        holder.tvSaveMoney.setText(readModel.getRead_fee() + "");
    }


    @Override
    public int getItemCount() {
        return readList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaterSn;
        TextView tvSaveMeasure;
        TextView tvSaveDate;
        TextView tvSaveHints;
        TextView tvSaveMoney;

        public MyViewHolder(View view) {
            super(view);
            tvWaterSn = view.findViewById(R.id.tvWaterSn);
            tvSaveMeasure = view.findViewById(R.id.tvSaveMeasure);
            tvSaveDate = view.findViewById(R.id.tvReChargeDate);
            tvSaveHints = view.findViewById(R.id.tvSaveHints);
            tvSaveMoney = view.findViewById(R.id.tvSaveMoney);
        }

    }
}

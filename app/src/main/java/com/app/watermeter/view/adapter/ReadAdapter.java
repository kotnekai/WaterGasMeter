package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
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

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_pre_save, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterReadModel preSaveModel = readList.get(position);
        if (preSaveModel == null) {
            return;
        }
//        holder.tvWaterType.setText(preSaveModel.getMeterSn());
//        holder.tvSaveMeasure.setText("50m3");
//        holder.tvSaveDate.setText(preSaveModel.getSaveTime());
//        holder.tvSaveMoney.setText(preSaveModel.getSaveMoney() + "");
    }


    @Override
    public int getItemCount() {
        return readList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWaterType;
        TextView tvSaveMeasure;
        TextView tvSaveDate;

        TextView tvSaveHints;

        TextView tvSaveMoney;

        public MyViewHolder(View view) {
            super(view);
            tvWaterType = view.findViewById(R.id.tvWaterType);
            tvSaveMeasure = view.findViewById(R.id.tvSaveMeasure);
            tvSaveDate = view.findViewById(R.id.tvSaveDate);
            tvSaveHints = view.findViewById(R.id.tvSaveHints);
            tvSaveMoney = view.findViewById(R.id.tvSaveMoney);
        }

    }
}

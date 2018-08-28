package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.model.PerSaveModel;
import com.app.watermeter.model.PerStorageModel;

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 */
public class PerSaveAdapter extends Adapter<PerSaveAdapter.MyViewHolder> {
    private List<PerSaveModel> preSaveModelList;
    private Context context;
    private LayoutInflater inflater;

    public PerSaveAdapter(Context context, List<PerSaveModel> preSaveModelList) {
        this.context = context;
        this.preSaveModelList = preSaveModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_pre_save, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PerSaveModel preSaveModel = preSaveModelList.get(position);
        if (preSaveModel == null) {
            return;
        }
        holder.tvWaterType.setText(preSaveModel.getMeterSn());
        holder.tvSaveMeasure.setText("50m3");
        holder.tvSaveDate.setText(preSaveModel.getSaveTime());
        holder.tvSaveMoney.setText(preSaveModel.getSaveMoney() + "");
    }


    @Override
    public int getItemCount() {
        return preSaveModelList.size();
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

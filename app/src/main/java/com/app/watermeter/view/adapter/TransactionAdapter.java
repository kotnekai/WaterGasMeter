package com.app.watermeter.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.common.CommonParams;
import com.app.watermeter.model.MeterTransactionModel;

import java.util.List;

/**
 * Create by Admin on 2018/8/27
 *
 * @author Tim
 */
public class TransactionAdapter extends Adapter<TransactionAdapter.MyViewHolder> {
    private List<MeterTransactionModel> reChargeList;
    private Context context;
    private LayoutInflater inflater;
    private int mTabType;
    private int meterType;


    public TransactionAdapter(Context context, List<MeterTransactionModel> reChargeList, int meterType) {
        this.context = context;
        this.reChargeList = reChargeList;
        inflater = LayoutInflater.from(context);
        this.meterType = meterType;

    }

    public void setData(List<MeterTransactionModel> list) {
        if (list != null && list.size() > 0) {
            this.reChargeList = list;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.item_transaction, parent, false);
        return new MyViewHolder(views);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MeterTransactionModel transactionModel = reChargeList.get(position);
        if (transactionModel == null) {
            return;
        }
        String typeStr;
        String unitStr = null;
        if (meterType == CommonParams.TYPE_ELECT) {
            typeStr = context.getString(R.string.electricity_sn);
            unitStr = "kw/h";
        } else if (mTabType == CommonParams.TYPE_GAS) {
            typeStr = context.getString(R.string.gas_sn);
            unitStr = "m³";
        } else {
            typeStr = context.getString(R.string.water_sn);
            unitStr = "m³";
        }
        holder.tvSn.setText(String.format(typeStr, transactionModel.getMachine_sn() + ""));
        if (transactionModel.getType().equals("read")) {
            holder.tvPaymentDetail.setVisibility(View.VISIBLE);
            holder.tvPaymentDetail.setText(String.format(context.getString(R.string.payment_details), transactionModel.getRvalue() + " " + unitStr));
            holder.tvAfter.setText(String.format(context.getString(R.string.pre_payment_balance), transactionModel.getAfter() + ""));
            holder.tvBefore.setText(String.format(context.getString(R.string.balance_after_payment), transactionModel.getBefore() + ""));
            holder.tvDate.setText(String.format(context.getString(R.string.payment_time), transactionModel.getCreated_at()));
            holder.tvMoney.setText("-" + transactionModel.getFee() + " " + context.getString(R.string.unit_yuan));
        } else {
            holder.tvPaymentDetail.setVisibility(View.GONE);
            holder.tvAfter.setText(String.format(context.getString(R.string.pre_payment_balance), transactionModel.getAfter() + ""));
            holder.tvBefore.setText(String.format(context.getString(R.string.balance_after_payment), transactionModel.getBefore() + ""));
            holder.tvDate.setText(String.format(context.getString(R.string.payment_time), transactionModel.getCreated_at()));
            holder.tvMoney.setText("+" + transactionModel.getFee() + " " + context.getString(R.string.unit_yuan));
        }
    }


    @Override
    public int getItemCount() {
        return reChargeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSn;
        TextView tvPaymentDetail;
        TextView tvAfter;
        TextView tvBefore;
        TextView tvDate;
        TextView tvMoney;

        public MyViewHolder(View view) {
            super(view);
            tvSn = view.findViewById(R.id.tvSn);
            tvPaymentDetail = view.findViewById(R.id.tvPaymentDetail);
            tvAfter = view.findViewById(R.id.tvAfter);
            tvBefore = view.findViewById(R.id.tvBefore);
            tvDate = view.findViewById(R.id.tvDate);
            tvMoney = view.findViewById(R.id.tvMoney);

        }

    }
}

package com.app.watermeter.utils;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.watermeter.R;
import com.app.watermeter.manager.MeterManager;
import com.app.watermeter.model.ApkInfoModel;
import com.app.watermeter.model.NetVersionModel;
import com.app.watermeter.model.VersionData;

public class DialogUtils {

    /**
     *确认弹窗，，只有一个确认按钮的
     * @param context
     * @param title
     * @param content
     */
    public static void showTipsDialog(Context context, String title, String content) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.tips_dialog_view, null);

        TextView tvOkBtn = (TextView) view.findViewById(R.id.tvOkBtn);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);
        if (title != null) {
            tvTitle.setText(title);
        }
        if (content != null) {
            tvContent.setText(content);
        }
        tvOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        dialog.setView(view);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) {
            return;
        }
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = UIUtils.dipToPx(context, 300);
        dialogWindow.setAttributes(lp);

        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
    public static void showConfirmDialog(Context context, String content, final View.OnClickListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.show_confirm_dialog, null);
        dialog.setView(view);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) {
            return;
        }
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
        lp.width = UIUtils.dipToPx(context, 300);
        //lp.height = UIUtils.dipToPx(context,200);
        dialogWindow.setAttributes(lp);
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView tvContent = (TextView) view.findViewById(R.id.tvContent);

        TextView tvCancel = (TextView) view.findViewById(R.id.tvCancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tvConfirm);
        tvContent.setText(content);


        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

    }

    public static void showApkUpdateDialog(final Context context, VersionData  apkInfoModel, final View.OnClickListener listener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(R.layout.apk_update_layout, null);
        TextView tvApkVersion = (TextView) view.findViewById(R.id.tvApkVersion);
        TextView tvUpdateTime = (TextView) view.findViewById(R.id.tvUpdateTime);
        TextView tvCancel = (TextView) view.findViewById(R.id.tvCancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tvConfirm);
        tvApkVersion.setText(context.getString(R.string.version_update_tip) + apkInfoModel.getVersion());
        tvUpdateTime.setText(context.getString(R.string.act_ver_update_time) + apkInfoModel.getUpdated_at());

        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

    }



    /**
     * 解绑提醒
     *
     * @param activity
     */
    public static void showUnBingHints(final Activity activity, final String sn) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(activity.getString(R.string.meter_unbind_hints))
                .setNegativeButton(activity.getString(R.string.cancel),null)
                .setPositiveButton(activity.getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MeterManager.getInstance().unbindMeter(sn);
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 解绑成功
     *
     * @param activity
     */
    public static void showUnBingSuccessHints(final Activity activity,String message) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(activity.getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}

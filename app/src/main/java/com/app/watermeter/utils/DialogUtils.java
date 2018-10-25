package com.app.watermeter.utils;
import android.content.Context;
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
import com.app.watermeter.model.ApkInfoModel;
import com.app.watermeter.model.NetVersionModel;

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

}

package com.ljstudio.pangpang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;


abstract class BaseDialog {

    protected WeakReference<Context> context;
    protected Dialog dialog;

    private View contentView;
    private boolean cancelable = true;

    protected BaseDialog(Context context, int layoutId) {
        this.context = new WeakReference<>(context);

        dialog = new Dialog(context, R.style.dialog_untran);
        contentView = dialog.getLayoutInflater().inflate(layoutId, null);
        dialog.setContentView(contentView);

        Window dialogWindow = dialog.getWindow();
//        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.CENTER);

        initEvents();
    }

    protected View findViewById(int id) {
        return dialog.findViewById(id);
    }

    protected abstract void onCancel();

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        try {
            dialog.dismiss();
        } catch (Exception ex) {

        }
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setWindowType(int type) {
        dialog.getWindow().setType(type);
    }

    public void setGravity(int gravity) {
        dialog.getWindow().setGravity(gravity);
    }

    private void initEvents() {
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cancelable) {
                    onCancel();
                    dismiss();
                }
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if (cancelable) {
                        onCancel();
                        dialog.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private float displayDensity;

    protected float dp2px(float dp) {
        if (displayDensity == 0) {
            if (context.get() != null) {
                displayDensity = context.get().getResources().getDisplayMetrics().density;
            }
        }
        return (dp * displayDensity + 0.1f);
    }

    protected Context getContext() {
        return context.get();
    }

}

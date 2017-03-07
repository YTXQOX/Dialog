package com.ljstudio.pangpang.dialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class TouchableTextView extends android.support.v7.widget.AppCompatTextView {

    public TouchableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isClickable()){
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        TouchableTextView.this.setAlpha(0.5f);
                    } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                        TouchableTextView.this.setAlpha(1.0f);
                    }
                }
                return false;
            }
        });
    }
}

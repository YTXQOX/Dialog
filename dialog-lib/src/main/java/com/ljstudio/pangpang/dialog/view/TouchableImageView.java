package com.ljstudio.pangpang.dialog.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchableImageView extends android.support.v7.widget.AppCompatImageView {

    public TouchableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isClickable()){
                    if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                        TouchableImageView.this.setAlpha(0.5f);
                    }else if (motionEvent.getAction()== MotionEvent.ACTION_UP || motionEvent.getAction()== MotionEvent.ACTION_CANCEL){
                        TouchableImageView.this.setAlpha(1.0f);
                    }
                }
                return false;
            }
        });
    }
}

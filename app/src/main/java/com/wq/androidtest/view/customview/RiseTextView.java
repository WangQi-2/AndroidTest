package com.wq.androidtest.view.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

/**
 * Created by wangqi on 15/9/15.
 * 仿支付宝,textview数字动态增长
 */
public class RiseTextView extends TextView {

    private static final int DURATION = 5000;
    private String text;

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
        setText("" + num);
    }

    private float num;
    private ObjectAnimator objectAnimator;

    public RiseTextView(Context context) {
        this(context, null);
        init();
    }

    private void init() {
        objectAnimator = ObjectAnimator.ofFloat(this, "num", 0f, num);
        objectAnimator.setDuration(DURATION);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(Animation.REVERSE);
    }

    public void start(){
        objectAnimator.start();
    }

    public RiseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RiseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}

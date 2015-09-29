package com.wq.androidtest.view.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wangqi on 15/9/15.
 * 仿支付宝,textview数字动态增长
 * fixme float很容易溢出
 */
public class RiseTextView extends TextView {

    private static final int DURATION = 1000;
    private static final int INT = 1;
    private static final int FLOAT = 2;
    final static int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE };
    private int numType;
    private float num;
    ValueAnimator valueAnimator;

    public void setNum(float num) {
        this.num = num;
        numType = FLOAT;
    }

    public void setNum(int num) {
        this.num = num;
        numType = INT;
    }

    private void init() {
    }

    int preLen;
    public void start() {
        valueAnimator = ValueAnimator.ofFloat(0f, num);
        valueAnimator.setDuration(DURATION);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float ret = (Float) valueAnimator.getAnimatedValue();
                switch (numType) {
                    case INT:
                        setText(String.format("%.0f", ret));
                        break;
                    case FLOAT:
                        setText(String.format("%.2f", ret));
                        break;
                }
            }
        });
        valueAnimator.start();
    }

    //获得整数部分长度
    static int sizeOfInt(float x) {
        for (int i = 0;; i++){
            if (x <= sizeTable[i])
                return i + 1;
        }
    }

    public RiseTextView(Context context) {
        this(context, null);
        init();
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

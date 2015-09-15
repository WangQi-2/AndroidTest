package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wangqi on 15/9/15.
 * 仿支付宝,textview数字动态增长
 */
public class RiseTextView extends TextView {

    private static final long DELTA_TIME = 200;
    private long duration;
    private String numText;
    private boolean isInt;


    public RiseTextView(Context context) {
        this(context, null);
    }

    public RiseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //TODO where use
    public RiseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setNumText(String s) {

        if (s.contains(".")) {
            isInt = false;
        } else {
            isInt = true;
        }
        this.numText = s;
    }


    public void setDuration(long millis) {
        this.duration = millis;
    }


    double numDouble;
    double delta;

    double cur = 0;

    public void start() {

        if (numText == null) {
            numText = getText().toString();
        }
        if (duration == 0) {
            duration = 10000;
        }

        numDouble = Double.parseDouble(numText);
        delta = numDouble / (double) duration / DELTA_TIME;

        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < duration / DELTA_TIME) {
                    try {
                        sleep(DELTA_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (isInt) {
                        setText((int) cur + "");
                    } else {
                        setText(cur + "");
                    }
                    cur = +delta;
                    i++;
                    invalidate();
                }
                setText(numText);
            }
        };
        post(thread);


//        CountDownTimer timer = new CountDownTimer(duration, DELTA_TIME) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//                if (isInt) {
//                    setText((int) cur + "");
//                } else {
//                    setText(cur + "");
//                }
//                cur = +delta;
//            }
//
//            @Override
//            public void onFinish() {
//                setText(numText);
//            }
//        };
//        timer.start();
    }
}

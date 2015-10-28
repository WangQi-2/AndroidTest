package com.wq.androidtest.activity.customview;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.view.customview.CircleProgressBar;

/**
 * Created by wangqi on 15/9/9.
 * 透明的圆形进度条
 */
public class CircleProgressActivity extends BaseActivity {

    int mProgress;
    CircleProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress);
        bar = (CircleProgressBar) findViewById(R.id.probar);

        CountDownTimer timer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mProgress >= 100) {
                    mProgress = 0;
                }
                bar.setProgress(mProgress++);
            }

            @Override
            public void onFinish() {
                bar.setProgress(100);
            }
        };
        timer.start();

    }
}

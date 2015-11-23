package com.wq.androidtest.activity.customview;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.view.customview.ProgressBarButton;

/**
 * Created by wangqi on 15/9/9.
 */
public class ProgressButtonActivity extends BaseActivity {
    int count;
    ProgressBarButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);
        btn = (ProgressBarButton) findViewById(R.id.probtn);
        btn.setProgress(60);
        CountDownTimer timer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn.setProgress(count++);
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
}

package com.wq.androidtest.activity.customview;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.view.customview.RiseTextView;

/**
 * Created by wangqi on 15/9/15.
 * 就是不断调整刷新textview
 */
public class RiseTextViewActivity extends BaseActivity {
    TextView textView;
    RiseTextView textView1;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_textview);
        textView = (TextView) findViewById(R.id.text1);
        textView.setText("" + 1000);
        CountDownTimer timer = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                //1
//                ToastUtil.showToast(mCtx, "threadId ontick:" + Thread.currentThread().getId());
                textView.setText(count++ + "");
            }

            @Override
            public void onFinish() {
                //1
//                ToastUtil.showToast(mCtx,"threadId onfinish:" + Thread.currentThread().getId());
                textView.setText(count++ + "");
            }
        };
        timer.start();
        textView1 = (RiseTextView) findViewById(R.id.text2);
        textView1.setNum(899999.90f);
        textView1.start();

    }
}

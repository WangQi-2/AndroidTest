package com.wq.androidtest.activity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.wq.androidlibrary.activity.BaseActivity;
import com.wq.androidtest.R;
import com.wq.androidtest.view.customview.RatingProgress;

/**
 * Created by wangqi on 15/9/9.
 */
public class RatingActivity extends BaseActivity {
    RatingProgress mRating;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        mRating = (RatingProgress) findViewById(R.id.ratingbar);
        CountDownTimer timer = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mRating.setRating(count);
                count++;
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
}

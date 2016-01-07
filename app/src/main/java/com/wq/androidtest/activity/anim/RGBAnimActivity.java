package com.wq.androidtest.activity.anim;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 1/7/16.
 */
public class RGBAnimActivity extends BaseActivity{
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb_anim);
        view = findViewById(R.id.view);

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view, "backgroundColor", 0xffff8080, 0xff8080ff,0xffff8080);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
//        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}

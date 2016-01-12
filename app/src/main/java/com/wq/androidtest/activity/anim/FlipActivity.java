package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.anim.FlipAnimation;

/**
 * Created by wangqi on 1/12/16.
 */
public class FlipActivity extends BaseActivity{

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip);
        view = findViewById(R.id.image);

        final FlipAnimation rotation =
                new FlipAnimation(0, 90, 0.0f, 540.0f);
        rotation.setDuration(4000);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnimation(rotation);
            }
        });
    }
}

package com.wq.androidtest.activity.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/24.
 */
// TODO: 15/11/24 andbase's animation
public class CardFlipViewActivity extends BaseActivity implements View.OnClickListener {

    private ImageView icon1;
    private ImageView icon2;

    private boolean isIcon1 = true;
    private static int DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);
        setUpViews();
    }

    private void setUpViews() {
        icon1 = (ImageView) findViewById(R.id.icon1);
        icon2 = (ImageView) findViewById(R.id.icon2);

        icon2.setVisibility(View.GONE);

        icon1.setOnClickListener(this);
        icon2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        flip();
    }

    private void flip() {
        final View showView = isIcon1 ? icon2 : icon1;
        final View hideView = isIcon1 ? icon1 : icon2;
        isIcon1 = !isIcon1;


        hideView.setRotationY(0);
        hideView.animate().rotationY(90).setDuration(DURATION).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideView.setVisibility(View.GONE);
                showView.setVisibility(View.VISIBLE);
                showView.setRotationY(-90);
                showView.animate().rotationY(0).setDuration(DURATION).setListener(null);
            }
        });
    }
}

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
public class CrossFadeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView icon1;
    private ImageView icon2;

    private boolean isIcon1 = true;
    private static int DURATION = 4000;

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
        changeImage();
    }


    private void changeImage() {
        View showView = isIcon1 ? icon2 : icon1;
        final View hideView = isIcon1 ? icon1 : icon2;
        isIcon1 = !isIcon1;

        showView.setAlpha(0);
        showView.setVisibility(View.VISIBLE);
        showView.animate().alpha(1f).setDuration(DURATION).setListener(null);

        hideView.animate().alpha(0f).setDuration(DURATION).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideView.setVisibility(View.GONE);
            }
        });
    }
}

package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.anim.FlipAnimation;

import java.util.ArrayList;

/**
 * Created by wangqi on 1/13/16.
 */
public class MoreFlipActivity extends BaseActivity implements View.OnClickListener {

    private static final int ANIMATION_DURATION = 300;
    View view1;
    View view2;
    View view3;
    View view4;
    View view5;
    View view6;

    ArrayList<View> viewList = new ArrayList<>();

    Button show;
    Button hide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreflip);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);
        view6 = findViewById(R.id.view6);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);

        show = (Button) findViewById(R.id.show);
        hide = (Button) findViewById(R.id.hide);
        show.setOnClickListener(this);
        hide.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                showViews();
                break;
            case R.id.hide:
                hideViews();
                break;
            default:
                break;
        }

    }

    double rate = 0.2d;

    private void hideViews() {
        for (int i = viewList.size(); i >= 0; i--) {
            final double position = i;
            final double delay = ANIMATION_DURATION * position * rate;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < viewList.size()) {
                        animateHideView((int) position);
                    }
                }
            }, (long) delay);
        }
    }

    private void showViews() {
        for (int i = viewList.size(); i >= 0; i--) {
            final double position = i;
            final double delay = ANIMATION_DURATION * position * rate;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (position < viewList.size()) {
                        animateShowView((int) position);
                    }
                }
            }, (long) delay);
        }

    }


    private void animateShowView(final int position) {
        final View view = viewList.get(position);
        FlipAnimation rotation =
                new FlipAnimation(90, 0, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        view.startAnimation(rotation);
    }

    private void animateHideView(final int position) {
        final View view = viewList.get(position);
        FlipAnimation rotation =
                new FlipAnimation(0, 90, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        view.startAnimation(rotation);
    }


}

package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.anim.CubeLeftOutAnimation;
import com.wq.androidtest.anim.CubeRightInAnimation;

/**
 * Created by wangqi on 15/10/2.
 */
public class CubeAnimActivity extends BaseActivity {

    static final long DURATION = 1000;

    View first;
    View second;
    View third;

    Animation cubeOutAnim;
    Animation cubeInAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_cube);
        initViews();
        initAnims();
    }

    private void initAnims() {
        cubeInAnim = new CubeRightInAnimation();
        cubeOutAnim = new CubeLeftOutAnimation();
        cubeInAnim.setDuration(DURATION);
        cubeInAnim.setFillAfter(true);
        cubeInAnim.setRepeatMode(Animation.REVERSE);
//        cubeInAnim.setRepeatCount(1);
        cubeOutAnim.setDuration(DURATION);
        cubeOutAnim.setFillAfter(true);
        cubeOutAnim.setRepeatMode(Animation.REVERSE);
//        cubeOutAnim.setRepeatCount(1);


    }

    private void initViews() {
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
    }

    private void startSecond() {
        cubeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                second.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.GONE);
        first.startAnimation(cubeOutAnim);
        second.startAnimation(cubeInAnim);
        // TODO: 15/10/2 下面四句无效
//        first.setAnimation(cubeOutAnim);
//        second.setAnimation(cubeInAnim);
//        cubeInAnim.start();
//        cubeOutAnim.start();
        // TODO: 15/10/2 what 4?
//        cubeOutAnim.startNow();
    }

    //TODO HOW TO RESTORE
    private void restoreAnim() {
        ToastUtil.showToast(this, "restore");
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.first:
                startSecond();
                break;
            case R.id.second:
                startThird();
                break;
            case R.id.third:
                startFirst();
                break;
        }
    }

    private void startFirst() {
        cubeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                first.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.GONE);
        third.setVisibility(View.VISIBLE);
        third.startAnimation(cubeOutAnim);
        first.startAnimation(cubeInAnim);

    }

    private void startThird() {
        cubeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                third.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        first.setVisibility(View.GONE);
        second.setVisibility(View.VISIBLE);
        third.setVisibility(View.VISIBLE);
        second.startAnimation(cubeOutAnim);
        third.startAnimation(cubeInAnim);

    }
}

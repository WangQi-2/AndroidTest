package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.anim.CubeLeftOutAnimation;
import com.wq.androidtest.anim.CubeRightInAnimation;

/**
 * Created by wangqi on 15/10/2.
 */
public class CubeAnimActivity extends BaseActivity {

    static final long DURATION = 5000;

    View cubeOutView;
    View cubeInView;

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
        cubeInAnim.setRepeatCount(1);
        cubeOutAnim.setDuration(DURATION);
        cubeOutAnim.setFillAfter(true);
        cubeOutAnim.setRepeatMode(Animation.REVERSE);
        cubeOutAnim.setRepeatCount(1);

        cubeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                cubeInView.bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void initViews() {
        cubeInView = findViewById(R.id.restore);
        cubeOutView = findViewById(R.id.start);
    }

    private void startAnim() {
        ToastUtil.showToast(this, "start");
        cubeInView.startAnimation(cubeInAnim);
        cubeOutView.startAnimation(cubeOutAnim);
        // TODO: 15/10/2 下面四句无效
//        cubeOutView.setAnimation(cubeOutAnim);
//        cubeInView.setAnimation(cubeInAnim);
//        cubeInAnim.start();
//        cubeOutAnim.start();
        // TODO: 15/10/2 what 4?
//        cubeOutAnim.startNow();
    }

    private void restoreAnim() {
        ToastUtil.showToast(this, "restore");
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.start:
                startAnim();
                break;
            case R.id.restore:
                restoreAnim();
                break;
        }
    }
}

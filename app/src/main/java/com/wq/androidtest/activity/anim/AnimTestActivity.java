package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.anim.CubeLeftOutAnimation;

/**
 * Created by wangqi on 15/10/2.
 */
public class AnimTestActivity extends BaseActivity {

    int DURATION = 20000;
    int INTERVAL = 100;
    int DEGREE = 90;

    Button animButton;
    int width;
    int height;

    AnimationSet animationSet;
    TranslateAnimation translateAnimation;
    RotateAnimation rotateAnimation;

    // TODO: 15/10/2 平移无效,再说了怎么做立体翻转
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);
        animButton = (Button) findViewById(R.id.anim);
        width = animButton.getMeasuredWidth();
        height = animButton.getMeasuredWidth();

        animationSet = new AnimationSet(true);
        translateAnimation = new TranslateAnimation(0,-width, 0,0);
        translateAnimation.setDuration(DURATION);
        translateAnimation.setFillAfter(true);
        rotateAnimation = new RotateAnimation(0,90);
        rotateAnimation.setDuration(DURATION);
        rotateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);

//        animationSet.addAnimation(rotateAnimation);
    }


    public void click(View view) {
//        animButton.startAnimation(translateAnimation);
//        CubeRightInAnimation animation = new CubeRightInAnimation();
        CubeLeftOutAnimation animation = new CubeLeftOutAnimation();
        animation.setDuration(DURATION);
        animation.setFillAfter(true);
        animButton.startAnimation(animation);
    }
}

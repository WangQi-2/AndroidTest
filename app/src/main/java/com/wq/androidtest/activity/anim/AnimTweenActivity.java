package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.util.ViewFinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/10/2.
 */
public class AnimTweenActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, Animation.AnimationListener {

    AlphaAnimation alphaAnimation;
    TranslateAnimation translateAnimation;
    ScaleAnimation scaleAnimation;
    RotateAnimation rotateAnimation;

    List<Animation> animList;


    ImageView imageView;

    CheckBox fillAfter;
    CheckBox reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);
        setUp();
    }

    private void setUp() {

        animList = new ArrayList<>();
        alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(mCtx, R.anim.tween_alpha);
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mCtx, R.anim.tween_rotate);
        scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(mCtx, R.anim.tween_scale);
        translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(mCtx, R.anim.tween_translation);
        imageView = ViewFinder.find(decorView, R.id.tween_image);

        alphaAnimation.setFillAfter(true);
        translateAnimation.setFillAfter(true);
        scaleAnimation.setFillAfter(true);
        rotateAnimation.setFillAfter(true);

        animList.add(alphaAnimation);
        animList.add(scaleAnimation);
        animList.add(rotateAnimation);
        animList.add(translateAnimation);

        fillAfter = ViewFinder.find(decorView, R.id.fill);
        reverse = ViewFinder.find(decorView, R.id.reverse);

        // not work; because these mothod are for animation, not view
        alphaAnimation.setFillEnabled(true);
        translateAnimation.setFillEnabled(true);
        scaleAnimation.setFillEnabled(true);
        rotateAnimation.setFillEnabled(true);

        alphaAnimation.setAnimationListener(this);
        translateAnimation.setAnimationListener(this);
        scaleAnimation.setAnimationListener(this);
        rotateAnimation.setAnimationListener(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            Interpolator interpolator = new BaseInterpolator() {
                @Override
                public float getInterpolation(float input) {
                    return 0;
                }
            };
        }

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.tween_alpha:
                imageView.startAnimation(alphaAnimation);
                break;
            case R.id.tween_rotate:
                imageView.startAnimation(rotateAnimation);
                break;
            case R.id.tween_scale:
                imageView.startAnimation(scaleAnimation);
                break;
            case R.id.tween_translate:
                imageView.startAnimation(translateAnimation);
                break;
            case R.id.tween_set:
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.fill:
                break;
            case R.id.reverse:
                if (isChecked) {
                    alphaAnimation.setRepeatMode(Animation.REVERSE);
                    translateAnimation.setRepeatMode(Animation.REVERSE);
                    scaleAnimation.setRepeatMode(Animation.REVERSE);
                    rotateAnimation.setRepeatMode(Animation.REVERSE);
                } else {
                    alphaAnimation.setRepeatMode(Animation.ABSOLUTE);
                    translateAnimation.setRepeatMode(Animation.ABSOLUTE);
                    scaleAnimation.setRepeatMode(Animation.ABSOLUTE);
                    rotateAnimation.setRepeatMode(Animation.ABSOLUTE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        int i = animList.indexOf(animation);
        imageView.startAnimation(animList.get((i + 1) % animList.size()));
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

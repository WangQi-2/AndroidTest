package com.wq.androidlibrary.util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by wangqi on 15/9/13.
 */
public class AnimationUtil {
    private final static long mDuration = 2000L;

    public static void enlargeView(View view,float scale){
        // TODO: 15/10/8 Change the view's z order in the tree, so it's on top of other sibling
        // TODO: 15/10/8 和linearlayout有什么关系
        view.bringToFront();

    }

    public static void scaleView(final View view, float scale){
        ScaleAnimation scaleAnimation = null;
        if(scale == 0)
        {
            return;
        }else if(scale > 0)
        {
            scaleAnimation = new ScaleAnimation(1.0f,scale,1.0f,scale, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        }
        scaleAnimation.setDuration(mDuration);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
    }

    public static void rotateView(View view){

        //true share interpolator
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(mDuration);
        //andbase has an error here
//        rotateAnimation.setInterpolator(view.getContext(), android.R.interpolator.accelerate_decelerate);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());
                animationSet.addAnimation(rotateAnimation);
        view.startAnimation(animationSet);
    }


    public static void playLandAnimation(final View view,final float offsetY) {
        float originalY =  - offsetY;
        float finalY = 0;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
        animationSet.setDuration(200);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //两秒后再调
                view.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        playJumpAnimation(view, offsetY);
                    }
                }, 2000);
            }
        });

        view.startAnimation(animationSet);
    }

    public static void playJumpAnimation(final View view,final float offsetY) {
        float originalY = 0;
        float finalY = - offsetY;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
        animationSet.setDuration(300);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                playLandAnimation(view, offsetY);
            }
        });

        view.startAnimation(animationSet);
    }
}

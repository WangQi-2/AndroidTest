package com.wq.androidtest.util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by wangqi on 15/9/13.
 */
public class AnimationUtil {
    private final static long mDuration = 2000L;

    public static void enlargeView(View view,float scale){
        //TODO bringToFront
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
}

package com.wq.androidtest.drawable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/9/22.
 */
public class CustomDrawable extends Drawable {

    Paint redPaint;
    Paint greenPaint;

    float scale;
    int alpha;
    AnimatorSet animatorSet;
    int duration = 5000;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        invalidateSelf();
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    public CustomDrawable() {
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.RED);
        redPaint.setAlpha(50);
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setAlpha(50);
        greenPaint.setColor(Color.GREEN);

        animatorSet = new AnimatorSet();
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(this, "scale", 0f, 1f);
        scaleAnim.setDuration(duration);
        scaleAnim.setRepeatCount(Animation.INFINITE);
        scaleAnim.setRepeatMode(Animation.REVERSE);
        scaleAnim.setInterpolator(new LinearInterpolator());
        ObjectAnimator alphaAnim = ObjectAnimator.ofInt(this, "alpha", 255, 0);
        alphaAnim.setDuration(duration);
        alphaAnim.setRepeatCount(Animation.INFINITE);
        alphaAnim.setRepeatMode(Animation.REVERSE);
        alphaAnim.setInterpolator(new LinearInterpolator());
        animatorSet.playTogether(scaleAnim, alphaAnim);
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO: 15/9/22  
        Rect rect = getBounds();
        float cx = rect.exactCenterX();
        float cy = rect.exactCenterY();
        canvas.drawRect(rect, greenPaint);
        float radius = Math.min(cx, cy);
        canvas.drawCircle(cx, cy, radius * scale, redPaint);
    }

    public void startAnimation() {
        if (animatorSet != null) {
            animatorSet.start();
        }

    }

    // TODO: 15/9/22
    @Override
    public void setAlpha(int alpha) {
        redPaint.setAlpha(alpha);
        greenPaint.setAlpha(alpha);
        // TODO: 15/9/23 这个log没有打出来
        Logger.e("***********************scale:" + scale + ", alpha:" + alpha);
        // TODO: 15/9/23  
        invalidateSelf();
    }

    // TODO: 15/9/22
    @Override
    public void setColorFilter(ColorFilter cf) {
        redPaint.setColorFilter(cf);
        greenPaint.setColorFilter(cf);
    }

    // TODO: 15/9/22
    @Override
    public int getOpacity() {
        return redPaint.getAlpha();
    }
}

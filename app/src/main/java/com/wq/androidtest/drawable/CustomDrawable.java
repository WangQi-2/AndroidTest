package com.wq.androidtest.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by wangqi on 15/9/22.
 */
public class CustomDrawable extends Drawable {

    Paint redPaint;
    Paint greenPaint;

    public CustomDrawable() {
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(Color.RED);
        redPaint.setAlpha(50);
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setAlpha(50);
        greenPaint.setColor(Color.GREEN);
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO: 15/9/22  
        Rect rect = getBounds();
        float cx = rect.exactCenterX();
        float cy = rect.exactCenterY();
        canvas.drawRect(rect, greenPaint);
        canvas.drawCircle(cx, cy, Math.min(cx, cy), redPaint);

    }

    // TODO: 15/9/22
    @Override
    public void setAlpha(int alpha) {
        redPaint.setAlpha(alpha);
        greenPaint.setAlpha(alpha);
    }

    // TODO: 15/9/22
    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    // TODO: 15/9/22
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}

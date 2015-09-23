package com.wq.androidtest.view.group;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by wangqi on 15/9/22.
 */
public class RippleLayout extends LinearLayout {

    int rippleColor = Color.BLUE;
    int width;
    int height;
    int circleRadius;
    int circleCenterX;
    int circleCenterY;
    boolean animing;
    Paint mPaint;


    public RippleLayout(Context context) {
        super(context);
        init();
    }

    public RippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(rippleColor);
        mPaint.setAlpha(50);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!animing) {
            circleRadius = 0;
            return;
        }

        int distanceX = Math.max(Math.abs(width - circleCenterX), Math.abs(circleCenterX));
        int distanceY = Math.max(Math.abs(height - circleCenterY), Math.abs(circleCenterY));

        //// TODO: 15/9/23 这里搞个动画来做可能更好
        if (circleRadius < Math.sqrt(distanceX * distanceX + distanceY * distanceY)) {
            canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, mPaint);
            circleRadius += 10;
            postInvalidateDelayed(10);
        } else {
            postInvalidate();
            animing = false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (!animing && event.getAction() == MotionEvent.ACTION_DOWN) {
            animing = true;
            circleCenterX = (int) event.getX();
            circleCenterY = (int) event.getY();
            postInvalidate();
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }
}

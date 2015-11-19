package com.wq.androidtest.activity.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangqi on 15/11/19.
 */
public class TickView extends View {

    int size = 1000;
    Paint mPaint;
    Paint mPaintRed;

    float p1x = 1f / 8;
    float p1y = 4f / 8;
    float p2x = 3f / 8;
    float p2y = 6f / 8;
    float p3x = 7f / 8;
    float p3y = 2f / 8;

    public TickView(Context context) {
        this(context, null);
    }

    public TickView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRed.setStrokeWidth(12);
        mPaintRed.setStyle(Paint.Style.STROKE);
        mPaintRed.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(size / 2, size / 2, size / 2 - 1, mPaint);
        int delta = size / 8;
        for (int i = 0; i <= 8; i++) {
            //draw line
            canvas.drawLine(0, i * delta, size, i * delta, mPaint);
            //draw column
            canvas.drawLine(i * delta, 0, i * delta, size, mPaint);
        }

        canvas.drawPath(p,mPaintRed);

    }

    Point p1;
    Point p2;
    Point p3;
    Path p;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(size, size);
        p1 = new Point((int) (size * p1x), (int) (size * p1y));
        p2 = new Point((int) (size * p2x), (int) (size * p2y));
        p3 = new Point((int) (size * p3x), (int) (size * p3y));

        p = new Path();
        p.moveTo(p1.x, p1.y);
        p.lineTo(p2.x, p2.y);
        p.lineTo(p3.x, p3.y);
    }

}

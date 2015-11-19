package com.wq.androidtest.view.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wq.androidlibrary.util.BitmapUtil;
import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/11/19.
 */
public class SunAndMoonView extends View {

    int size = 1080;
    Bitmap sun;
    Bitmap moon;
    float sunAngle = 0;
    float deltaAngle = 180;
    float movingAngle = 0;

    public SunAndMoonView(Context context) {
        super(context);
    }

    public SunAndMoonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SunAndMoonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    int bmpSize = 150;

    private void init() {
        sun = BitmapFactory.decodeResource(getResources(), R.drawable.sun);
        sun = BitmapUtil.getScaleBitmap(sun, bmpSize, bmpSize);
        moon = BitmapFactory.decodeResource(getResources(), R.drawable.moon_new);
        moon = BitmapUtil.getScaleBitmap(moon, bmpSize, bmpSize);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        // TODO: 15/11/19  dashpatheffect
        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4, 5}, 1f);
        mPaint.setPathEffect(dashPathEffect);
    }

    public void setMovingAngle(int angle) {
        this.movingAngle = angle;
        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(size, size);
        radius = (size - bmpSize) / 2;
    }

    Paint mPaint;
    float radius;


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(size / 2, size / 2, radius, mPaint);
        double posY = size / 2 - radius * Math.sin(Math.toRadians(sunAngle + movingAngle)) - bmpSize / 2;
        double posX = size / 2 + radius * Math.cos(Math.toRadians(sunAngle + movingAngle)) - bmpSize / 2;
        canvas.drawBitmap(sun, (float) posX, (float) posY, mPaint);
        posY = size / 2 - radius * Math.sin(Math.toRadians(sunAngle + deltaAngle + movingAngle)) - bmpSize / 2;
        posX = size / 2 + radius * Math.cos(Math.toRadians(sunAngle + deltaAngle + movingAngle)) - bmpSize / 2;
        canvas.drawBitmap(moon, (float) posX, (float) posY, mPaint);
    }


    float lastX;
    float lastY;

    // TODO: 15/11/19 rotate flow finger
    // TODO: 15/11/19 add MathUtil,add method getDegreeBy3Point
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();


                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}

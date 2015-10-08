package com.wq.androidtest.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.wq.androidtest.R;


/**
 */
public class CircleProgressBar extends View {

    private final String TAG = getClass().getSimpleName();

    int mViewWidth;
    int mViewHeight;

    // 半径、颜色，透明度，开始角度
    int mCircleRadius;
    int mCircleColor;
    int mCircleAlpha;
    int mStartAngle;
    int mBorderWidth;
    // 是否绘制出外部区域
    boolean isDrawOutter;
    // 圆心距离view中心的距离，可正可负
    int mCentreOffsetX;
    int mCentreOffsetY;

    Paint mPaint;
    int mProgress;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CircleProgressBar);
        mCircleRadius = (int) a.getDimensionPixelSize(R.styleable.CircleProgressBar_circle_radius,
                0);
        mBorderWidth = (int) a.getDimensionPixelSize(
                R.styleable.CircleProgressBar_circle_border_width, 4);
        mCircleColor = a
                .getColor(R.styleable.CircleProgressBar_circle_color, Color.BLACK);
        mCircleAlpha = (int) a.getInt(R.styleable.CircleProgressBar_circle_alpha, 70);
        if (mCircleAlpha > 70 || mCircleAlpha < 0) {
            mCircleAlpha = 70;
        }

        mStartAngle = (int) a.getInt(R.styleable.CircleProgressBar_circle_start_angle,
                -90);

        mCentreOffsetX = (int) a.getDimensionPixelSize(
                R.styleable.CircleProgressBar_centre_offset_x, 0);
        mCentreOffsetY = (int) a.getDimensionPixelSize(
                R.styleable.CircleProgressBar_centre_offset_y, 0);
        mProgress = (int) a.getInt(R.styleable.CircleProgressBar_circle_progress, 0);
        isDrawOutter = a.getBoolean(R.styleable.CircleProgressBar_circle_draw_outter,
                false);
        if (mProgress < 0) {
            mProgress = 0;
        } else if (mProgress > 100) {
            mProgress = 100;
        }
        a.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        if (progress > 100) {
            mProgress = 100;
        }
        if (progress < 0) {
            mProgress = 0;
        }
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (mProgress == 100) {
            return;
        }

        mPaint.setColor(mCircleColor);
        mPaint.setAlpha(mCircleAlpha * 255 / 100);

        if (isDrawOutter) {
            drawOutter(canvas);
        }

        drawProgressCircle(canvas);
    }

    private void drawOutter(Canvas canvas) {

        Bitmap target = Bitmap.createBitmap(mViewWidth, mViewHeight,
                Bitmap.Config.ARGB_8888);

        Canvas outterCanvas = new Canvas(target);
        outterCanvas.drawRect(0, 0, mViewWidth, mViewHeight, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        outterCanvas.drawCircle(mViewWidth / 2, mViewHeight / 2, mCircleRadius
                + mBorderWidth, mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(target, 0, 0, mPaint);
    }

    private void drawProgressCircle(Canvas canvas) {
        int cx = mViewWidth / 2 + mCentreOffsetX + getPaddingLeft();
        int cy = mViewHeight / 2 + mCentreOffsetY + getPaddingTop();
        RectF rectF = new RectF(cx - mCircleRadius, cy - mCircleRadius, cx
                + mCircleRadius, cy + mCircleRadius);
        float sweepAngle = (100f - mProgress) * 360 / 100;
        canvas.drawArc(rectF, mStartAngle + mProgress * 360f / 100, sweepAngle, true,
                mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        mViewWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();

        if (mCircleRadius == 0) {
            mCircleRadius = mViewWidth / 2 - mBorderWidth;
        }
    }
}

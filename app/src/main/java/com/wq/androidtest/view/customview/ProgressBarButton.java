package com.wq.androidtest.view.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;

/**
 * 带有进度条的button,进度带有动画
 */
public class ProgressBarButton extends Button {

    int mProgress;
    int mBackgroundColor;
    // 交替线条颜色
    int mLineColor1;
    int mLineColor2;
    // 斜线宽度
    int mLineWidth;
    int mFocusedBorderColor;
    int mUnFocusedBorderColor;
    int mFocusedBorderWidth;
    int mUnFocusedBorderWidth;
    int mTextFocusedColor;
    int mTextUnFocusedColor;
    // 按钮外边阴影宽度
    int mShadowWidth;
    // 进度条高度
    int mProgressHeight;

    Paint mPaint;
    Canvas mCanvas;
    boolean mHasFocus = false;
    Resources mResoures;
    // 图形每次移动距离
    int mClipOffset;
    // 待剪裁bitmap
    Bitmap mOriginalBgBitmap;
    private int viewHeight;
    private int viewWidth;

    public ProgressBarButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ProgressBarButton);
        mResoures = context.getResources();
        mProgress = a.getInt(R.styleable.ProgressBarButton_progress, 0);
        if (mProgress > 100 || mProgress < 0) {
            mProgress = 0;
        }

        int backgroundColor = mResoures.getColor(R.color.btn_bg_color);
        mBackgroundColor = a.getColor(R.styleable.ProgressBarButton_background_color,
                backgroundColor);

        int color1 = mResoures.getColor(R.color.btn_progress_color_one);
        int color2 = mResoures.getColor(R.color.btn_progress_color_two);
        mLineColor1 = a.getColor(R.styleable.ProgressBarButton_line_color_1, color1);
        mLineColor2 = a.getColor(R.styleable.ProgressBarButton_line_color_2, color2);

        mLineWidth = a.getInt(R.styleable.ProgressBarButton_line_width, 10);

        int focusedBorderColor = mResoures.getColor(R.color.btn_border_color_focused);
        int unfocusedBorderColor = mResoures.getColor(R.color.btn_border_color_unfocused);
        mFocusedBorderColor = a.getColor(
                R.styleable.ProgressBarButton_border_color_focused, focusedBorderColor);
        mUnFocusedBorderColor = a.getColor(
                R.styleable.ProgressBarButton_border_color_unfocused,
                unfocusedBorderColor);

        // TODO: 15/10/8 getint?? 
        mFocusedBorderWidth = a.getInt(
                R.styleable.ProgressBarButton_border_width_focused, 3);
        mUnFocusedBorderWidth = a.getInt(
                R.styleable.ProgressBarButton_border_width_unfocused, 1);

        int textFocusedColor = mResoures.getColor(R.color.btn_text_color_focused);
        int textUnFocusedColor = mResoures.getColor(R.color.btn_text_color_unfocused);
        mTextFocusedColor = a.getColor(R.styleable.ProgressBarButton_text_color_focused,
                textFocusedColor);
        mTextUnFocusedColor = a.getColor(
                R.styleable.ProgressBarButton_text_color_unfocused, textUnFocusedColor);

        mShadowWidth = a.getDimensionPixelSize(
                R.styleable.ProgressBarButton_shadow_width, 0);
        if (mShadowWidth < 0) {
            mShadowWidth = 0;
        }
        a.recycle();
        mCanvas = new Canvas();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Style.FILL);
        setTextColor(mTextUnFocusedColor);
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ProgressBarButton.this.mHasFocus = hasFocus;
                if (hasFocus) {
                    setTextColor(mTextFocusedColor);
                } else {
                    setTextColor(mTextUnFocusedColor);
                }
            }
        });

        setBackgroundColor(Color.TRANSPARENT);
    }

    public void setProgressBgColor(int color) {
        mBackgroundColor = color;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        RectF rectF = new RectF(0 + 2 + mShadowWidth, 0 + 2 + mShadowWidth, viewWidth - 2
                - mShadowWidth, viewHeight - 2 - mShadowWidth);

        mPaint.setColor(mBackgroundColor);
        canvas.drawRoundRect(rectF, mProgressHeight / 2, mProgressHeight / 2, mPaint);

        mPaint.reset();
        Bitmap bitmap = clipRectBitmap();
        bitmap = clipRoundRectBitmap(bitmap);

        drawProgress(canvas, bitmap);
        drawFrame(canvas);
        drawShadow(canvas);

        super.onDraw(canvas);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    invalidate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.setPriority(Thread.MAX_PRIORITY);
        post(thread);

    }

    // 绘制进度
    private void drawProgress(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }

    // 绘制阴影
    private void drawShadow(Canvas canvas) {
        if (mShadowWidth == 0) {
            return;
        }

        Paint shadowPaint = new Paint();
        shadowPaint.setStyle(Style.STROKE);
        shadowPaint.setStrokeWidth(1);
        shadowPaint.setColor(Color.BLACK);
        shadowPaint.setAntiAlias(true);
        float alphaStep = 255 / 5f / mShadowWidth;
        float alpha = 0;
        RectF shadowRectF = new RectF();
        for (int i = 0; i < mShadowWidth; i++) {
            shadowRectF.left = i;
            shadowRectF.top = i;
            shadowRectF.right = viewWidth - i;
            shadowRectF.bottom = viewHeight - i;
            alpha += alphaStep;
            shadowPaint.setAlpha((int) alpha);
            canvas.drawRoundRect(shadowRectF, viewHeight / 2 - i, viewHeight / 2 - i,
                    shadowPaint);
        }
    }

    public void setProgress(int progress) {

        if (progress < 0 || progress > 100) {
            return;
        }
        this.mProgress = progress;
        postInvalidate();

    }

    private void drawFrame(Canvas canvas) {

        int frameColor;
        int frameWidth;

        RectF rectF;

        if (mHasFocus) {
            frameColor = mFocusedBorderColor;
            frameWidth = mFocusedBorderWidth;
            rectF = new RectF(0 + frameWidth / 2 + mShadowWidth, 0 + frameWidth / 2
                    + mShadowWidth, viewWidth - frameWidth / 2 - mShadowWidth, viewHeight
                    - frameWidth / 2 - mShadowWidth);
        } else {
            frameColor = mUnFocusedBorderColor;
            frameWidth = mUnFocusedBorderWidth;
            rectF = new RectF(0 + frameWidth / 2 + 2 + mShadowWidth, 0 + frameWidth / 2
                    + 2 + mShadowWidth, viewWidth - frameWidth / 2 - 2 - mShadowWidth,
                    viewHeight - frameWidth / 2 - 2 - mShadowWidth);
        }

        mPaint.setAntiAlias(true);
        mPaint.setColor(frameColor);
        mPaint.setStrokeWidth(frameWidth);
        mPaint.setStyle(Style.STROKE);

        canvas.drawRoundRect(rectF, mProgressHeight / 2, mProgressHeight / 2, mPaint);
        mPaint.reset();
    }

    private Bitmap createBigBitmap() {
        int bitmapWidth = viewWidth + viewHeight * 3;
        int bitmapHeight = (int) (viewHeight + mLineWidth * Math.sqrt(mLineWidth) + 2);

        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight,
                Bitmap.Config.ARGB_4444);
        mCanvas.setBitmap(bitmap);
        mPaint.setStrokeWidth(mLineWidth);

        int startX = 0;
        int startY = 0;
        int endX = bitmapHeight;
        int endY = bitmapHeight;
        for (int i = 0; i < bitmapWidth / mLineWidth * Math.sqrt(2); i++) {
            if (i % 2 == 0) {
                mPaint.setColor(mLineColor1);
            } else {
                mPaint.setColor(mLineColor2);
            }
            mCanvas.drawLine(startX, startY, endX, endY, mPaint);
            startX += mLineWidth * Math.sqrt(2);
            endX = startX + bitmapHeight;
        }
        mPaint.reset();
        return bitmap;
    }

    private Bitmap clipRectBitmap() {

        mClipOffset += 5;
        if (mClipOffset > mLineWidth * 2) {
            mClipOffset = 0;
        }

        int clipWidth = (int) (((float) mProgress / 100) * viewWidth);
        if (clipWidth == 0) {
            clipWidth = 1;
        }
        int clipHeight = viewHeight;
        int clipStartX = mClipOffset + viewHeight;
        int clipStartY = (int) (mLineWidth * Math.sqrt(2) / 2 + 1);
        Bitmap bitmap = Bitmap.createBitmap(mOriginalBgBitmap, clipStartX, clipStartY,
                clipWidth, clipHeight);
        return bitmap;
    }

    private Bitmap clipRoundRectBitmap(Bitmap bitmap) {

        Path rectfPath = new Path();
        RectF rectF = new RectF(0 + 2 + mShadowWidth, 0 + 2 + mShadowWidth, viewWidth - 2
                - mShadowWidth, viewHeight - 2 - mShadowWidth);
        rectfPath.addRoundRect(rectF, mProgressHeight / 2, mProgressHeight / 2,
                Path.Direction.CCW);
        Region roundRegion = new Region();
        roundRegion.setPath(rectfPath, new Region(0, 0, mProgress * viewWidth / 100,
                viewHeight));

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        for (int i = 0; i < viewWidth; i++) {
            for (int j = 0; j < viewHeight; j++) {
                if (!roundRegion.contains(i, j) && i < bitmapWidth && j < bitmapHeight) {
                    bitmap.setPixel(i, j, Color.TRANSPARENT);
                }
            }
        }
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);

        mOriginalBgBitmap = createBigBitmap();
        if (mShadowWidth * 3 > viewHeight) {
            mShadowWidth = viewHeight / 3;
        }
        mProgressHeight = viewHeight - mShadowWidth * 2;
    }


}

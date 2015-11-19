package com.wq.androidtest.view.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wq.androidlibrary.util.BitmapUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/11/19.
 */
public class IndicatorView extends View {

    private int mSpace;
    private int mItemSize;
    private Bitmap mItemBmp;
    private Bitmap mItemBmpSelected;
    private int mItemCount;
    private int mItemPosSelected;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mMatrix = new Matrix();
        mItemPosSelected = 0;
        mItemCount = 5;
        mSpace = 50;
        mItemSize = 120;
        mItemBmp = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.sun);
        mItemBmp = BitmapUtil.getScaleBitmap(mItemBmp, mItemSize, mItemSize);
        mItemBmpSelected = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.moon_new);
        mItemBmpSelected = BitmapUtil.getScaleBitmap(mItemBmpSelected, mItemSize, mItemSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mItemSize;
        int width = mItemCount * (mSpace + mItemSize) - mSpace;
        setMeasuredDimension(width, height);
    }

    private Matrix mMatrix;
    private Paint mPaint;

    @Override
    protected void onDraw(Canvas canvas) {

        if (mItemBmpSelected == null || mItemBmp == null) {
            Logger.e("itemSelected:" + mItemBmpSelected + ", item : " + mItemBmp);
            return;
        }

        for (int i = 0; i < mItemCount; i++) {
            canvas.drawBitmap(mItemBmp, i * (mSpace + mItemSize), 0, mPaint);
        }
        canvas.drawBitmap(mItemBmpSelected, (mItemPosSelected + mMovingProgress) * (mSpace + mItemSize), 0, mPaint);
    }

    float mMovingProgress = 0f;

    public void moveToNext() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mMovingProgress = (Float) animation.getAnimatedValue();
                if (mItemPosSelected == mItemCount - 1 && mMovingProgress > 0.5f) {
                    mItemPosSelected = -1;
                }
                if (mMovingProgress == 1f) {
                    mMovingProgress = 0f;
                    mItemPosSelected++;
                    animator.cancel();
                }
                invalidate();
            }
        });

        animator.start();
    }

    public void moveToPrevious() {
        final ValueAnimator animator = ValueAnimator.ofFloat(0f, -1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mMovingProgress = (Float) animation.getAnimatedValue();

                if (mItemPosSelected == 0 && mMovingProgress < -0.5f) {
                    mItemPosSelected = mItemCount;
                }

                if (mMovingProgress == -1f) {
                    mMovingProgress = 0f;
                    mItemPosSelected--;
                    animator.cancel();
                }
                invalidate();
            }
        });

        animator.start();
    }
}

package com.wq.androidtest.view.focus;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.wq.androidtest.R;


/**
 * TODO 优化
 * 评分条 目前仅支持step 为0.5  仅支持显示isindicator
 * 
 * @author qiwang
 */
public class TvRatingBar extends View {
    
    private static String TAG = TvRatingBar.class.getSimpleName();
    // 前景图星星
    private Bitmap mFBmp;
    // 背景图星星
    private Bitmap mBBmp;
    // 图片宽度
    private int mDrawableWidth;
    // 图片高度
    private int mDrawableHeight;
    // 图片间间距
    private int mDrawableSpacing;
    // 图片个数
    private int mCount;
    // 最大进度
    private int mMaxProgress;
    // 当前进度
    private int mCurProgress;
    
    // 控件高宽
    private int mViewWidth;
    private int mViewHeight;
    // 实际rating占用高宽
    private int mRatingWidth;
    private int mRatingHeight;
    
    private Paint mPaint;
    private Matrix mMatrix;
    private Bitmap mForegroundBar;
    private Bitmap mBackgroundBar;
    private Bitmap mCurRatingBar;
    
    public TvRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TvRatingBar);
        Drawable mForegoundDrawable = a
                .getDrawable(R.styleable.TvRatingBar_drawable_foreground);
        mFBmp = ((BitmapDrawable) mForegoundDrawable).getBitmap();
        mDrawableHeight = mFBmp.getHeight();
        mDrawableWidth = mFBmp.getWidth();
        Drawable mBackgoundDrawable = a
                .getDrawable(R.styleable.TvRatingBar_drawable_background);
        mBBmp = ((BitmapDrawable) mBackgoundDrawable).getBitmap();
        mDrawableSpacing = a.getDimensionPixelSize(
                R.styleable.TvRatingBar_drawable_spacing, 0);
        mCount = a.getInteger(R.styleable.TvRatingBar_drawable_count, 0);
        mMaxProgress = a.getInteger(R.styleable.TvRatingBar_drawable_max_progress, 0);
        mCurProgress = a.getInteger(R.styleable.TvRatingBar_drawable_cur_progress, 0);
        
        a.recycle();
        
        mRatingHeight = mDrawableHeight;
        mRatingWidth = mDrawableWidth * mCount + mDrawableSpacing * (mCount - 1);
        
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMatrix = new Matrix();
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        
        mViewWidth = mDrawableWidth * mCount + mDrawableSpacing * (mCount - 1)
                + getPaddingLeft() + getPaddingRight();
        mViewHeight = mDrawableHeight + getPaddingTop() + getPaddingBottom();
        
        if (widthMode == MeasureSpec.EXACTLY) {
            mViewWidth = widthSize;
        }
        
        if (widthMode == MeasureSpec.AT_MOST) {
            mViewWidth = Math.min(mViewWidth, widthSize);
        }
        
        if (heightMode == MeasureSpec.EXACTLY) {
            mViewHeight = heightSize;
        }
        
        if (heightMode == MeasureSpec.AT_MOST) {
            mViewHeight = Math.min(mViewHeight, heightSize);
        }
        
        setMeasuredDimension(mViewWidth, mViewHeight);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        if (mForegroundBar == null) {
            mForegroundBar = generateRatingBar(mFBmp);
        }
        if (mBackgroundBar == null) {
            mBackgroundBar = generateRatingBar(mBBmp);
        }
        
        canvas.translate(getPaddingLeft(), getPaddingTop());
        canvas.drawBitmap(mBackgroundBar, mMatrix, mPaint);
        
        Bitmap bitmap = generateProgressBar();
        canvas.drawBitmap(bitmap, mMatrix, mPaint);
    }
    
    private Bitmap generateProgressBar() {
        int curCount = (int) (mCount * ((float) mCurProgress / mMaxProgress));
        int width = 0;
        if (mCurProgress % 2 == 1) {
            width = (mDrawableWidth + mDrawableSpacing) * curCount + mDrawableWidth / 2;
        }
        else {
            width = (mDrawableWidth + mDrawableSpacing) * curCount;
        }
        
        return Bitmap.createBitmap(mForegroundBar, 0, 0, width, mRatingHeight);
    }
    
    private Bitmap generateRatingBar(Bitmap starBmp) {
        Bitmap ratingBarBmp = Bitmap.createBitmap(mRatingWidth, mRatingHeight,
                Config.ARGB_8888);
        Canvas canvas = new Canvas(ratingBarBmp);
        canvas.translate(getPaddingLeft(), getPaddingTop());
        for (int i = 0; i < mCount; i++) {
            if (i != 0) {
                canvas.translate((mDrawableWidth + mDrawableSpacing), 0);
            }
            canvas.drawBitmap(starBmp, mMatrix, mPaint);
        }
        canvas.save();
        canvas.restore();
        return ratingBarBmp;
    }
    
    public void setRating(int rating) {
        mCurProgress = rating;
        invalidate();
    }
}

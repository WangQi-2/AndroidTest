package com.wq.androidtest.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/9/20.
 * 一个可以设置颜色的圆形实心控件,没什么屌用
 */
public class CircleView extends View {

    private int mColor = Color.RED;
    // TODO: 15/9/20 还有哪些flag
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    //// TODO: 15/9/20  diff with next
    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //// TODO: 15/9/20  
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
        Logger.e("in init()");
    }

    //主要处理一下wrap_content,因为没有content
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heihtSpceSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST) {
            widthSpecSize = 200;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST) {
            heihtSpceSize = 200;
        }
        setMeasuredDimension(widthSpecSize, heihtSpceSize);
        Logger.e("width :" + widthSpecSize);
        Logger.e("height :" + heihtSpceSize);
    }

    //// TODO: 15/9/20 padding测量是否要拿出来
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height)/2;
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
    }
}

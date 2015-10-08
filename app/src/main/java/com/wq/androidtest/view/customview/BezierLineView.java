package com.wq.androidtest.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.wq.androidlibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/10/8.
 * todo 15/10/8 简单的贝塞尔曲线，但是多点贝塞尔曲线怎么实现？
 */
public class BezierLineView extends View {

    public BezierLineView(Context context) {
        super(context);
    }

    public BezierLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    Paint mPaint;
    Path mPath;
    List<Point> mPoint;
    int width;
    int height;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        // TODO: 15/10/8 3种style
        mPaint.setStyle(Paint.Style.STROKE);
        mPoint = new ArrayList<>();
        mPath = new Path();
        mPath.moveTo(0, 0);

        for (int i = 0; i < 10; i++) {
            Point point = new Point(i * width / 10, (int)(height * Math.random()));
            mPoint.add(point);
        }
        for (int i = 0; i < 10 - 1; i++) {
            if(i > 0)
            {
                mPath.moveTo(mPoint.get(i-1).x, mPoint.get(i-1).y);
            }
            mPath.quadTo(mPoint.get(i).x, mPoint.get(i).y, mPoint.get(i + 1).x, mPoint.get(i + 1).y);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        canvas.drawColor(Color.GRAY);
        canvas.drawPath(mPath, mPaint);
        String str = "";
        for (Point p : mPoint) {
            canvas.drawText(p.x + ":" + p.y, p.x, p.y, mPaint);
            str+= p.x + ":" + p.y + "\n";
        }
        ToastUtil.showToast(getContext(),str);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }
}

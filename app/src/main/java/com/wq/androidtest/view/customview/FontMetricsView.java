package com.wq.androidtest.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangqi on 15/9/21.
 * TODO 文本绘制先等等,先搞定view吧
 */
public class FontMetricsView extends View {

    int width = 800;
    int heigth = 250;

    public FontMetricsView(Context context) {
        super(context);
    }

    public FontMetricsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FontMetricsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(35);
        textPaint.setColor(Color.WHITE);

// FontMetrics对象
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        String text = "abcdefghijklmnopqrstu";
        // 计算每一个坐标
        float baseX = 0;
        float baseY = 100;
        float topY = baseY + fontMetrics.top;
        float ascentY = baseY + fontMetrics.ascent;
        float descentY = baseY + fontMetrics.descent;
        float bottomY = baseY + fontMetrics.bottom;

// 绘制文本
        canvas.drawText(text, baseX, baseY, textPaint);

// BaseLine描画
        Paint baseLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        baseLinePaint.setColor(Color.RED);
        canvas.drawLine(0, baseY, getWidth(), baseY, baseLinePaint);

// Base描画
        canvas.drawCircle(baseX, baseY, 5, baseLinePaint);

// TopLine描画
        Paint topLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        topLinePaint.setColor(Color.LTGRAY);
        canvas.drawLine(0, topY, getWidth(), topY, topLinePaint);

// AscentLine描画
        Paint ascentLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ascentLinePaint.setColor(Color.GREEN);
        canvas.drawLine(0, ascentY, getWidth(), ascentY, ascentLinePaint);

// DescentLine描画
        Paint descentLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        descentLinePaint.setColor(Color.YELLOW);
        canvas.drawLine(0, descentY, getWidth(), descentY, descentLinePaint);

// ButtomLine描画
        Paint bottomLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bottomLinePaint.setColor(Color.MAGENTA);
        canvas.drawLine(0, bottomY, getWidth(), bottomY, bottomLinePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, heigth);
    }
}

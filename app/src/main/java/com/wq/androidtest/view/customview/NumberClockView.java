package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wangqi on 15/9/21.
 * todo 数字时// 计算每一个坐标
 float baseX = 0;
 float baseY = 100;
 float topY = baseY + fontMetrics.top;
 float ascentY = baseY + fontMetrics.ascent;
 float descentY = baseY + fontMetrics.descent;
 float bottomY = baseY + fontMetrics.bottom;

 // 绘制文本
 canvas.drawText( text, baseX, baseY, textPaint);

 // BaseLine描画
 Paint baseLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);>
 baseLinePaint.setColor( Color.RED);
 canvas.drawLine(0, baseY, getWidth(), baseY, baseLinePaint);

 // Base描画
 canvas.drawCircle( baseX, baseY, 5, baseLinePaint);

 // TopLine描画
 Paint topLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
 topLinePaint.setColor( Color.LTGRAY);
 canvas.drawLine(0, topY, getWidth(), topY, topLinePaint);

 // AscentLine描画
 Paint ascentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
 ascentLinePaint.setColor( Color.GREEN);
 canvas.drawLine(0, ascentY, getWidth(), ascentY, ascentLinePaint);

 // DescentLine描画
 Paint descentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
 descentLinePaint.setColor( Color.YELLOW);
 canvas.drawLine(0, descentY, getWidth(), descentY, descentLinePaint);

 // ButtomLine描画
 Paint bottomLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
 bottomLinePaint.setColor( Color.MAGENTA);
 canvas.drawLine(0, bottomY, getWidth(), bottomY, bottomLinePaint);
 */
public class NumberClockView extends TextView {

    public NumberClockView(Context context) {
        super(context);
        init();
    }


    public NumberClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

    }

}

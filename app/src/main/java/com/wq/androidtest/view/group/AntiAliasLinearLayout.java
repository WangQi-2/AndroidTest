package com.wq.androidtest.view.group;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wangqi on 15/12/23.
 */
public class AntiAliasLinearLayout extends LinearLayout{
    public AntiAliasLinearLayout(Context context) {
        super(context);
    }

    public AntiAliasLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AntiAliasLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(1, Paint.ANTI_ALIAS_FLAG));
        super.dispatchDraw(canvas);
    }
}

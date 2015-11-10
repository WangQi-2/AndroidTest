package com.wq.androidtest.view.customview;

import android.content.Context;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/11/6.
 */
public class TVHorizontalGridView extends HorizontalGridView {

    public TVHorizontalGridView(Context context) {
        super(context);
    }

    public TVHorizontalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TVHorizontalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    final static int offset = 20;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int width = getWidth();
        int height = getHeight();
        Logger.e("x504","width : " + width);
        Logger.e("x504","height : " + height);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {


        float eventX = event.getX();
        int width = getWidth();
        Logger.e("x504","eventX : " + eventX);
        Logger.e("x504","width : " + width);
        if (eventX + offset > width) {
            scrollBy(offset, 0);
        }
        if (eventX - offset < 0) {
            scrollBy(-offset, 0);
        }
        return super.onHoverEvent(event);
    }
}

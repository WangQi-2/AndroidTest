package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by wangqi on 15/9/21.
 */
public class slidingView extends TextView {

    private int mSlop;
    private int mLastX;
    private int mLastY;

    public slidingView(Context context) {
        super(context);
        init();
    }

    public slidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public slidingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // TODO: 15/9/21 viewconfiguration 
        mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int deltaX = x - mLastX;
            int deltaY = y - mLastY;
            int transX = (int) (ViewHelper.getTranslationX(this) + deltaX);
            int transY = (int) (ViewHelper.getTranslationY(this) + deltaY);
            // TODO: 15/9/21 nine old, orignal ?
            ViewHelper.setTranslationX(this, transX);
            ViewHelper.setTranslationY(this, transY);
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}

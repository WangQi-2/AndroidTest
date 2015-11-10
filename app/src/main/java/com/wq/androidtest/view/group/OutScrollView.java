package com.wq.androidtest.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.wq.androidtest.util.Logger;


/**
 * Created by wangqi on 15/9/16.
 * 处理滑动冲突问题
 */
public class OutScrollView extends ScrollView {

    private GestureDetector mDetector;

    public OutScrollView(Context context) {
        this(context, null);
    }

    public OutScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (Math.abs(distanceY) > Math.abs(distanceX)) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {

        float eventX = event.getX();
        float eventY = event.getY();
        int childW = getChildAt(0).getWidth();
        int childH = getChildAt(0).getHeight();
        int width = getWidth();
        int height = getHeight();
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        Logger.e("x504", "eventX : " + eventX + ", eventY : " + eventY);
        Logger.e("x504", "width : " + width + ", height : " + height);
        Logger.e("x504", "scrollX : " + scrollX + ", scrollY : " + scrollY);
        if(eventY + 20 > height)
        {
           scrollBy(0,20);
        }
        if(eventY -20 < 0)
        {
            scrollBy(0,-20);
        }
        return super.onHoverEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //TODO 15/10/08 为什么,我觉得被注释的代码就应该OK了??
//        return mDetector.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev) && mDetector.onTouchEvent(ev);
//        return super.onInterceptTouchEvent(ev);
    }
}

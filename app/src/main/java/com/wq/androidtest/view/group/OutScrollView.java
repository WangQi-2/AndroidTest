package com.wq.androidtest.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;


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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //TODO 为什么,我觉得被注释的代码就应该OK了??
//        return mDetector.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev) && mDetector.onTouchEvent(ev);
    }
}

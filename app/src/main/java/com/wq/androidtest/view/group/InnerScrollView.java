package com.wq.androidtest.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by wangqi on 15/9/28.
 *
 */

public class InnerScrollView extends ScrollView {
    public InnerScrollView(Context context) {
        super(context);
    }

    public InnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float lastX = 0;
    float lastY = 0;
// TODO: 15/9/30 也许这个类根本是不需要的
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
//        boolean consumed = true;
//
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                lastX = ev.getX();
//                lastY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float deltaY = ev.getY() - lastY;
//                int scrollY = getScrollY();
//                int height = getHeight();
//                int maxHeight = getChildAt(0).getMeasuredHeight();
//                String info = "scrollY:" + scrollY + ", height:" + height + ", maxHeight:" + maxHeight;
//                Logger.e(info);
//                consumed = ViewCompat.canScrollVertically(this, (int) deltaY);
//                break;
//        }
//
//        if (consumed) {
//            super.onTouchEvent(ev);
//        }
//        return consumed;
//    }

}

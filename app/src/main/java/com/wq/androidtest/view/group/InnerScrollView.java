package com.wq.androidtest.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by wangqi on 15/9/28.
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

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        boolean consumed = true;

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
//
//                if (deltaY < 0 && scrollY + height >= maxHeight) {
//                    consumed = false;
//                }
//                if (deltaY > 0 && scrollY == 0) {
//                    consumed = false;
//                }
//                break;
//        }
        return super.onTouchEvent(ev);

    }
}

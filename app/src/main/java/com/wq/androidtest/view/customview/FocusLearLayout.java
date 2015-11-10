package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by wangqi on 15/11/6.
 */
public class FocusLearLayout extends LinearLayout {
    public FocusLearLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    public FocusLearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusLearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_HOVER_ENTER) {
            requestFocus();
        }
        return super.onHoverEvent(event);
    }
}

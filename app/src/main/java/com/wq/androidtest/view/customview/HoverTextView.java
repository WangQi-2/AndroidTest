package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by wangqi on 15/10/31.
 */
public class HoverTextView extends Button {


    public HoverTextView(Context context) {
        super(context);
        init();
    }

    public HoverTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HoverTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void onHoverChanged(boolean hovered) {
        super.onHoverChanged(hovered);
        requestFocus();
    }
}

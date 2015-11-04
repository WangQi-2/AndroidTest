package com.wq.androidtest.view.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/11/2.
 */
public class FocusLayout extends FrameLayout {
    public FocusLayout(Context context) {
        super(context);
        init();
    }

    public FocusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(true);
        setClickable(true);
        setFocusableInTouchMode(true);
    }


    @Override
    public void onHoverChanged(boolean hovered) {
        if (hovered) {
            ToastUtil.showToast(getContext(),"hovered");
            requestFocus();
        }
        super.onHoverChanged(hovered);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int action = event.getAction();
        Logger.d("onKeyUp action : " + action);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int action = event.getAction();
        Logger.d("onKeyDown action : " + action);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Logger.d("onTouchEvent action : " + action);
        return super.onTouchEvent(event);
    }
}

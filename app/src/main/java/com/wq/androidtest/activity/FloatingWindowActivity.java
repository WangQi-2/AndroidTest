package com.wq.androidtest.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/10/19.
 */
public class FloatingWindowActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_window);
    }

    public void click(View view) {
        createFloatingView();
    }

    WindowManager mWindowManager;

    private void createFloatingView() {
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = mWindowManager.getDefaultDisplay().getWidth();
        int height = mWindowManager.getDefaultDisplay().getHeight();
        FrameLayout smallWindow = new FrameLayout(this);
        TextView textView = new TextView(this);
        textView.setText("Window");
        smallWindow.addView(textView);
        WindowManager.LayoutParams smallWindowParams = new WindowManager.LayoutParams();
        smallWindowParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        smallWindowParams.format = PixelFormat.RGBA_8888;
        smallWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
        smallWindowParams.width = 200;
        smallWindowParams.height = 200;
        smallWindowParams.x = width;
        smallWindowParams.y = height / 2;
        mWindowManager.addView(smallWindow, smallWindowParams);
        mWindowManager.updateViewLayout(smallWindow, smallWindowParams);
    }

}

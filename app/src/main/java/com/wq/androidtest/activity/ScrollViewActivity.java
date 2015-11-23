package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.util.Logger;
import com.wq.androidtest.view.group.OutScrollView;

/**
 * Created by wangqi on 15/11/6.
 */
public class ScrollViewActivity extends BaseActivity {

    OutScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        scrollView = (OutScrollView) findViewById(R.id.scrollView);
        Logger.e("x504","test");
        scrollView.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                Logger.e("x504","onhover");
                return false;
            }
        });
    }
}

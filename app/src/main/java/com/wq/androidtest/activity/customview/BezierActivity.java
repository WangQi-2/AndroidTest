package com.wq.androidtest.activity.customview;

import android.os.Bundle;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/10/8.
 */
public class BezierActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
    }
}
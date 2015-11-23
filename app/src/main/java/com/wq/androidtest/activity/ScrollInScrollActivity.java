package com.wq.androidtest.activity;

import android.os.Bundle;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/16.
 */
public class ScrollInScrollActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 优化:当内部scroll滚到头的时候,让外部的滚起来
        setContentView(R.layout.activity_scroll_in_scroll);
    }
}

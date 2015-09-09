package com.wq.androidtest.activity;

import android.os.Bundle;

import com.wq.androidlibrary.activity.BaseActivity;
import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/9/9.
 */
public class RatingActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
    }
}

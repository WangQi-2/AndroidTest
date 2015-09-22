package com.wq.androidtest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.drawable.CustomDrawable;

/**
 * Created by wangqi on 15/9/22.
 */
public class CustomDrawableActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customdrawable);
        TextView text = (TextView) findViewById(R.id.text);
        text.setBackgroundDrawable(new CustomDrawable());
    }
}

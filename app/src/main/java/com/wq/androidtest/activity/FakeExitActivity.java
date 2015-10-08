package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/9/23.
 */
public class FakeExitActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_exit);
    }

    public void click(View view) {
        moveTaskToBack(true);
    }
}

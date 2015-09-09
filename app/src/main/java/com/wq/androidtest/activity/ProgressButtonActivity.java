package com.wq.androidtest.activity;

import android.os.Bundle;

import com.wq.androidlibrary.activity.BaseActivity;
import com.wq.androidtest.R;
import com.wq.androidtest.view.focus.ProgressBarButton;

/**
 * Created by wangqi on 15/9/9.
 */
public class ProgressButtonActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);
        ProgressBarButton btn = (ProgressBarButton) findViewById(R.id.probtn);
        btn.setProgress(60);
    }
}

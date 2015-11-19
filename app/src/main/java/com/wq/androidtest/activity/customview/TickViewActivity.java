package com.wq.androidtest.activity.customview;

import android.os.Bundle;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.util.ViewFinder;

/**
 * Created by wangqi on 15/11/19.
 */
public class TickViewActivity extends BaseActivity {
    TickView tickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick);
        tickView = ViewFinder.find(decorView, R.id.tick);
    }
}

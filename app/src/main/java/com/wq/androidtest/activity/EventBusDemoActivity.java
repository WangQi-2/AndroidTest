package com.wq.androidtest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.wq.androidlibrary.activity.BaseActivity;
import com.wq.androidtest.R;

import de.greenrobot.event.EventBus;

/**
 * Created by wangqi on 15/9/8.
 * test eventbus
 */
public class EventBusDemoActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_demo);
    }

}

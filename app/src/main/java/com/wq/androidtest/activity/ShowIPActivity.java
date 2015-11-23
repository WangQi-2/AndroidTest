package com.wq.androidtest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wq.androidlibrary.util.NetWorkUtil;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/7.
 * show ip and mac address
 */
public class ShowIPActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);
        textView.setText(NetWorkUtil.getLocalIp(this));
    }
}

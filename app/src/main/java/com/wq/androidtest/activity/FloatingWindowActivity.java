package com.wq.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.service.FxService;
import com.wq.androidtest.service.MonitorService;

/**
 * Created by wangqi on 15/10/19.
 */
public class FloatingWindowActivity extends BaseActivity implements View.OnClickListener {

    Button showBtn;
    Button rmBtn;
    Button showMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_window);
        showBtn = (Button) findViewById(R.id.show_fw);
        rmBtn = (Button) findViewById(R.id.remove_fw);
        showMonitor = (Button) findViewById(R.id.show_monitor);
        showBtn.setOnClickListener(this);
        rmBtn.setOnClickListener(this);
        showMonitor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.show_monitor:
                i = new Intent(this, MonitorService.class);
                startService(i);
                finish();
                moveTaskToBack(true);
                break;
            case R.id.show_fw:
                i = new Intent(this, FxService.class);
                startService(i);
                finish();
                moveTaskToBack(true);
                break;
            case R.id.remove_fw:
                i = new Intent(this, FxService.class);
                stopService(i);
                break;
        }
    }
}

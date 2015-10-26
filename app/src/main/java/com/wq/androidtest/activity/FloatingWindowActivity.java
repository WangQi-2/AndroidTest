package com.wq.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.service.FxService;

/**
 * Created by wangqi on 15/10/19.
 */
public class FloatingWindowActivity extends BaseActivity implements View.OnClickListener {

    Button showBtn;
    Button rmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_window);
        showBtn = (Button) findViewById(R.id.show_fw);
        rmBtn = (Button) findViewById(R.id.remove_fw);
        showBtn.setOnClickListener(this);
        rmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.show_fw:
                i = new Intent(this, FxService.class);
                startService(i);
                moveTaskToBack(true);
                break;
            case R.id.remove_fw:
                i = new Intent(this, FxService.class);
                stopService(i);
                break;
        }
    }
}

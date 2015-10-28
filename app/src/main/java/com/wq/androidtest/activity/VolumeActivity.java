package com.wq.androidtest.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/10/28.
 */
public class VolumeActivity extends BaseActivity{
    TextView show;
    Button ring;
    Button media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
    }

    @Override
    protected void findView() {

    }


}

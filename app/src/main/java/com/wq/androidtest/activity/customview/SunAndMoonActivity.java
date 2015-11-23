package com.wq.androidtest.activity.customview;

import android.os.Bundle;
import android.widget.SeekBar;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.view.customview.SunAndMoonView;

/**
 * Created by wangqi on 15/11/19.
 */
public class SunAndMoonActivity extends BaseActivity{

    SeekBar seekBar;
    SunAndMoonView sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sun_and_moon);
        seekBar = (SeekBar) findViewById(R.id.seek);
        sun = (SunAndMoonView) findViewById(R.id.sun);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sun.setMovingAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

package com.wq.androidtest.activity.system;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/10/28.
 */
public class VolumeActivity extends BaseActivity {

    String RING = "ring";
    String MUSIC = "music";
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        initViews();
        setListeners();
    }

    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
    }

    private void setListeners() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {

                }

            }
        });
    }


}

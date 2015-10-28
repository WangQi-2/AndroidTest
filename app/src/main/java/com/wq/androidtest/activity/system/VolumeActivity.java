package com.wq.androidtest.activity.system;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/10/28.
 */
public class VolumeActivity extends BaseActivity {

    TextView textView;
    CheckBox checkBox;
    String RING = "ring";
    String MUSIC = "music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        initViews();
        setListeners();
        monitorRing();
    }

    private void setListeners() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    monitorRing();
                } else {
                    monitorMedia();
                }
            }
        });
    }

    private void monitorMedia() {
        textView.setText(MUSIC);
        // TODO: 15/10/28 test all
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    private void monitorRing() {
        textView.setText(RING);
        setVolumeControlStream(AudioManager.STREAM_RING);
    }

    private void initViews() {
        textView = (TextView) findViewById(R.id.text);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
    }


}

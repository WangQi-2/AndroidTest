package com.wq.androidtest.activity.system;

import android.media.AudioManager;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

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
                    case R.id.music:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
                        break;
                    case R.id.ring:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_RING);
                        break;
                    case R.id.alarm:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_ALARM);
                        break;
                    case R.id.notification:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
                        break;
                    case R.id.system:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_SYSTEM);
                        break;
                    case R.id.voicecall:
                        VolumeActivity.this.setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
                        break;

                }

            }
        });
    }


}

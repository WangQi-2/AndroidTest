package com.wq.androidtest.activity.system;

import android.os.Bundle;
import android.widget.SeekBar;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/11/21.
 */
public class SeekBarActivity extends BaseActivity {

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                ToastUtil.showToast(mCtx, "start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ToastUtil.showToast(mCtx, "stop");
            }
        });
    }
}

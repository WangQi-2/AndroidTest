package com.wq.androidtest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wq.androidlibrary.util.DeviceUtil;

/**
 * Created by wangqi on 15/9/16.
 */
public class DeviceInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        setContentView(textView);
        textView.setText(
                "deviceId :" + DeviceUtil.getDeviceId(this) +
                        "\nmac :" + DeviceUtil.getMacAddress(this) +
                        "\nandroidid :" + DeviceUtil.getAndroidId(this) +
                        "\nserial :" + DeviceUtil.getAndroidId(this)
        );
    }
}

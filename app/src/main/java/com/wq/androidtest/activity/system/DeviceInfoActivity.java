package com.wq.androidtest.activity.system;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.widget.TextView;

import com.wq.androidlibrary.util.DeviceUtil;
import com.wq.androidtest.activity.base.BaseActivity;

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

        StringBuffer sb = new StringBuffer();
        sb.append(
                "deviceId :" + DeviceUtil.getDeviceId(this) +
                        "\nmac :" + DeviceUtil.getMacAddress(this) +
                        "\nandroidid :" + DeviceUtil.getAndroidId(this) +
                        "\nserial :" + DeviceUtil.getAndroidId(this)
        );
        sb.append("\n");

        PixelFormat pixelFormat = new PixelFormat();
        PixelFormat.getPixelFormatInfo(PixelFormat.RGBA_8888, pixelFormat);
        sb.append("\nRGBA_8888 bytesPerPixel:" + pixelFormat.bytesPerPixel);
        PixelFormat.getPixelFormatInfo(PixelFormat.RGBX_8888, pixelFormat);
        sb.append("\nRGBX_8888 bytesPerPixel:" + pixelFormat.bytesPerPixel);
        PixelFormat.getPixelFormatInfo(PixelFormat.RGB_888, pixelFormat);
        sb.append("\nRGB_888 bytesPerPixel:" + pixelFormat.bytesPerPixel);
        PixelFormat.getPixelFormatInfo(PixelFormat.RGB_565, pixelFormat);
        sb.append("\nRGB_565 bytesPerPixel:" + pixelFormat.bytesPerPixel);

        textView.setText(
                sb.toString()
        );
    }
}

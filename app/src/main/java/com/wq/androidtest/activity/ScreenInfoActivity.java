package com.wq.androidtest.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.ScreenUtil;

/**
 * Created by wangqi on 15/9/11.
 */
public class ScreenInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screeninfo);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        int densityDpi = displayMetrics.densityDpi;
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        float scaledDensity = displayMetrics.scaledDensity;
        float xdpi = displayMetrics.xdpi;
        float ydpi = displayMetrics.ydpi;
        TextView info = (TextView) findViewById(R.id.text);
        String str = "density:" + density + "\n" +
                "densityDpi:" + densityDpi + "\n" +
                "heightPixels:" + heightPixels + "\n" +
                "widthPixels:" + widthPixels + "\n" +
                "scaleDensity:" + scaledDensity + "\n" +
                "xdpi:" + xdpi + "\n" +
                "ydpi:" + ydpi + "\n" +
                "width in inch:" + ScreenUtil.getScreenWidthInInch(this) + "\n" +
                "height in inch:" + ScreenUtil.getScreenHeigthInInch(this) + "\n" +
                "size in inch:" + ScreenUtil.getScreenSizeInInch(this) + "\n";
        info.setText(str);
    }
}

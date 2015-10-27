package com.wq.androidtest.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.TrafficStats;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wq.androidlibrary.util.AppUtil;
import com.wq.androidlibrary.util.StringUtil;
import com.wq.androidtest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MonitorService extends Service {

    LinearLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    WindowManager mWindowManager;


    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();
        showData();
        AppUtil.getm(this);
    }

    long totalRxBytes;
    Map<String, Long> pkgLastRxBytes = new HashMap<>();
    public static final String ANDROIDTEST = "com.wq.androidtest";
    public static final String TVMARKET = "com.pplive.tvmarket";

    private void showData() {
        new CountDownTimer(Integer.MAX_VALUE, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("total: " + StringUtil.FormatDecimal((TrafficStats.getTotalRxBytes() - totalRxBytes) / 1024f / 1024f) + "MB/s" + "");
                totalRxBytes = TrafficStats.getTotalRxBytes();

                List<ActivityManager.RunningAppProcessInfo> allApps = AppUtil.getAllApps(MonitorService.this);
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : allApps) {
                    if (runningAppProcessInfo.processName.equals(ANDROIDTEST)) {
                        if (pkgLastRxBytes.containsKey(ANDROIDTEST)) {
                            textView2.setText("ADtest: " + StringUtil.FormatDecimal((TrafficStats.getUidRxBytes(runningAppProcessInfo.uid) - pkgLastRxBytes.get(ANDROIDTEST)) / 1024f / 1024f) + "MB/s" + "");
                        }
                        pkgLastRxBytes.put(ANDROIDTEST, new Long(TrafficStats.getUidRxBytes(runningAppProcessInfo.uid)));
                    }
                    if (runningAppProcessInfo.processName.equals(TVMARKET)) {
                        if (pkgLastRxBytes.containsKey(TVMARKET)) {
                            textView3.setText("market: " + StringUtil.FormatDecimal((TrafficStats.getUidRxBytes(runningAppProcessInfo.uid) - pkgLastRxBytes.get(TVMARKET)) / 1024f / 1024f) + "MB/s" + "");
                        }
                        pkgLastRxBytes.put(TVMARKET, new Long(TrafficStats.getUidRxBytes(runningAppProcessInfo.uid)));
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        wmParams.gravity = Gravity.LEFT | Gravity.TOP;

        wmParams.x = 0;
        wmParams.y = 0;


        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.layout_monitor, null);
        mWindowManager.addView(mFloatLayout, wmParams);

        textView1 = (TextView) mFloatLayout.findViewById(R.id.text1);
        textView2 = (TextView) mFloatLayout.findViewById(R.id.text2);
        textView3 = (TextView) mFloatLayout.findViewById(R.id.text3);
        textView4 = (TextView) mFloatLayout.findViewById(R.id.text4);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFloatLayout != null) {
            mWindowManager.removeView(mFloatLayout);
        }
    }

}

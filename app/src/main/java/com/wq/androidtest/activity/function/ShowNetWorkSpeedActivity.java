package com.wq.androidtest.activity.function;

import android.net.TrafficStats;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.wq.androidlibrary.util.NetWorkUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/10/26.
 */
public class ShowNetWorkSpeedActivity extends BaseActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    long beforeRx;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_network_speed);
        initView();

        CountDownTimer timer = new CountDownTimer(Integer.MAX_VALUE, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView1.setText("count :" + count++);
                //fake speed
                textView2.setText("cur speed: " + NetWorkUtil.getWifiSpeed(ShowNetWorkSpeedActivity.this) + " Mb/s" + " by WifiManager");

                //by TrafficStats
                textView3.setText("cur speed: " + (TrafficStats.getTotalRxBytes() - beforeRx)/1024f/1024f/0.5 + "MB/s" + " by TrafficStats");
                textView4.setText("cur speed: " + beforeRx/1024f/1024f/0.5 + "Mb/s" + " by TrafficStats");

                beforeRx = TrafficStats.getTotalRxBytes();

            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }

    private void initView() {
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);
    }
}

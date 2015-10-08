package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/10/2.
 */
public class AnimTestActivity extends BaseActivity {

    int DURATION = 20000;
    int INTERVAL = 100;
    int DEGREE = 90;

    Button animButton;
    int width;
    int height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_test);
        animButton = (Button) findViewById(R.id.anim);
        width = animButton.getMeasuredWidth();
        height = animButton.getMeasuredWidth();

    }


    public void click(View view) {
    }
}

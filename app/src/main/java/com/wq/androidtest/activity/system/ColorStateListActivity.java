package com.wq.androidtest.activity.system;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 1/13/16.
 */
public class ColorStateListActivity extends BaseActivity {

    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorstatelist);


        ColorStateList csl = getResources().getColorStateList(R.color.seletor_btn);
        ColorStateList test1 = getResources().getColorStateList(R.color.seletor_test1);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        if (csl != null) {
            btn1.setTextColor(csl);
            btn2.setTextColor(csl);
            if (Build.VERSION.SDK_INT >= 21) {
                btn1.setBackgroundTintList(test1);
                btn2.setBackgroundTintList(test1);
            }
        }
    }
}

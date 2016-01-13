package com.wq.androidtest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 1/12/16.
 */
public class DrawerLayoutActivity extends BaseActivity {

    android.support.v4.widget.DrawerLayout drawerLayout;
    View leftView;
    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        drawerLayout = (android.support.v4.widget.DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.RED);
        leftView = findViewById(R.id.left_drawer);
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        content = (FrameLayout) findViewById(R.id.content_frame);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });


    }
}

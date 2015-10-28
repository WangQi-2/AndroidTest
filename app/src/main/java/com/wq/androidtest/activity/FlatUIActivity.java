package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.view.flatui.FlatButton;
import com.wq.androidtest.view.flatui.FlatUI;

/**
 * Created by wangqi on 15/9/30.
 */
public class FlatUIActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatui);
        FlatUI.addFlatView((FlatButton) findViewById(R.id.blue));
        FlatUI.addFlatView((FlatButton) findViewById(R.id.green));
        FlatUI.addFlatView((FlatButton) findViewById(R.id.red));
    }

    @Override
    protected void findView() {

    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.blue:
                FlatUI.changeTheme(getResources().getColor(R.color.blue3));
                break;
            case R.id.green:
                FlatUI.changeTheme(getResources().getColor(R.color.green));
                break;
            case R.id.red:
                FlatUI.changeTheme(getResources().getColor(R.color.red));
                break;
        }
    }
}

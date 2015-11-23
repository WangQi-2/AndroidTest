package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/22.
 */
public class RippleLayoutActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripplelayout);
    }

    public void onclick(View view){
        ToastUtil.showToast(this, "clicked");
    }
}

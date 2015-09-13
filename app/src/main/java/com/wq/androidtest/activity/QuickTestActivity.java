package com.wq.androidtest.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.wq.androidtest.util.AppUtil;

public class QuickTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, AppUtil.hasInstallPermission(this) + "", Toast.LENGTH_LONG).show();
    }
}

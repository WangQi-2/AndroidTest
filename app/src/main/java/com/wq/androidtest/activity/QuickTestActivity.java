package com.wq.androidtest.activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.Toast;

import com.wq.androidlibrary.util.AppUtil;

public class QuickTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.wq.androidtest",0);
            Toast.makeText(this, AppUtil.isSystemApp(this, info) + "", Toast.LENGTH_LONG).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ViewCompat compat;
    }
}

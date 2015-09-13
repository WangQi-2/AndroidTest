package com.wq.androidtest.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.wq.androidlibrary.R;
import com.wq.androidlibrary.manager.ActivityUtil;

/**
 * Created by qiwang on 2015/8/27.
 * 继承次activity
 */
public class BaseActivity extends Activity {
    //全局对象
    Application mApp;
    Context mCtx;
    Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化
        mApp = getApplication();
        mCtx = this;
        mRes = getResources();
        ActivityUtil.getInstance().addActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        ActivityUtil.getInstance().addActivity(this);
    }
}

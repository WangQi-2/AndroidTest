package com.wq.androidlibrary.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;

import com.wq.androidlibrary.manager.ActivityUtil;

/**
 * Created by qiwang on 2015/8/27.
 * 继承次activity
 */
public abstract class BaseActivity extends Activity {
    //全局对象
    Application mApp;
    Context mCtx;
    Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mApp = getApplication();
        mCtx = this;
        mRes = getResources();
        ActivityUtil.getInstance().addActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        ActivityUtil.getInstance().addActivity(this);
    }
}

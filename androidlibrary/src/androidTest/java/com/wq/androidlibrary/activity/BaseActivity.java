package com.wq.androidlibrary.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Created by qiwang on 2015/8/27.
 * 继承次activity
 */
public abstract class BaseActivity extends Activity {
    //已初始化的全局对象
    Application mApp;
    Context mCtx;
    Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = getApplication();
        mCtx = this;
        mRes = getResources();
    }
}

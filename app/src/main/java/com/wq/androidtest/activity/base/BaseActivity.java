package com.wq.androidtest.activity.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.wq.androidlibrary.R;
import com.wq.androidlibrary.util.ActivityUtil;

/**
 * Created by qiwang on 2015/8/27.
 * 继承次activity
 */
public class BaseActivity extends Activity {
    protected String TAG = this.getClass().getSimpleName();
    public static final String FUNC_MODELS = "func_models";
    public static final String TITLE = "title";
    //全局对象
    protected Application mApp;
    protected Context mCtx;
    protected Resources mRes;
    protected View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        if (i != null) {
            String title = i.getStringExtra(TITLE);
            if (!TextUtils.isEmpty(title)) {
                ActionBar actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
            }
        }

        //初始化
        mApp = getApplication();
        mCtx = this;
        mRes = getResources();
        ActivityUtil.getInstance().addActivity(this);
        decorView = getWindow().getDecorView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        ActivityUtil.getInstance().addActivity(this);
    }
}

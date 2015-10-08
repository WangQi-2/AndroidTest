package com.wq.androidtest.base;

import android.app.Application;

/**
 * Created by wangqi on 15/9/20.
 */
public class TestApplication extends Application {

    private static TestApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;


        //crash
//        CrashHandler.getInstance().init(this);
    }

    public static TestApplication getsInstance() {
        return sInstance;
    }
}

package com.wq.androidlibrary.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiwang on 2015/8/27.
 * activity管理类，退出app时，先退出所有activity
 * 单例模式
 */
public class ActivityManager {
    private List<Activity> mActivites;
    private static ActivityManager mInstance;

    private ActivityManager() {
        mActivites = new ArrayList<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            mInstance = new ActivityManager();
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        mActivites.add(activity);
    }

    public void finishAllActivities() {
        for (Activity activity : mActivites) {
            activity.finish();
        }
    }

    public void removeActivity(Activity activity){
        mActivites.remove(activity);
    }
}

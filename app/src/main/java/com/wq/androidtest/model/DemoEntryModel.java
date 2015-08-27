package com.wq.androidtest.model;

import android.app.Activity;

/**
 * Created by qiwang on 2015/8/27.
 */
public class DemoEntryModel {

    private String des;
    private Activity activity;

    public DemoEntryModel(String des, Activity activity) {
        this.des = des;
        this.activity = activity;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}

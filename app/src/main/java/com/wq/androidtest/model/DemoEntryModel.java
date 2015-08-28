package com.wq.androidtest.model;

import android.app.Activity;

/**
 * Created by qiwang on 2015/8/27.
 */
public class DemoEntryModel {

    private String des;
    private Class clazz;

    public DemoEntryModel(String des, Class clazz) {
        this.des = des;
        this.clazz = clazz;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}

package com.wq.androidtest.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qiwang on 2015/8/27.
 */
public class DemoEntryModel implements Serializable {

    private String des;
    private Class clazz;
    private List<DemoEntryModel> childs;


    public DemoEntryModel(String des, Class clazz) {
        this.des = des;
        this.clazz = clazz;
    }

    public DemoEntryModel(String des, Class clazz, List<DemoEntryModel> childs) {
        this.des = des;
        this.clazz = clazz;
        this.childs = childs;
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

    public List<DemoEntryModel> getChilds() {
        return childs;
    }

    public void setChilds(List<DemoEntryModel> childs) {
        this.childs = childs;
    }
}

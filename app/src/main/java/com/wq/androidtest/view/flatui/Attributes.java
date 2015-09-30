package com.wq.androidtest.view.flatui;

/**
 * Created by wangqi on 15/9/30.
 */
public class Attributes {

    private int color;
    private AttributesChangeListener listener;


    public AttributesChangeListener getListener() {
        return listener;
    }

    public void setListener(AttributesChangeListener listener) {
        this.listener = listener;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        if (listener != null) {
            listener.onThemeChange();
        }
    }


    public interface AttributesChangeListener {
        public void onThemeChange();
    }


}

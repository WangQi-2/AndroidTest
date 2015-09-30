package com.wq.androidtest.view.flatui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by wangqi on 15/9/30.
 * fixme 加入动画特效
 */
public class FlatButton extends Button implements FlatView, Attributes.AttributesChangeListener {

    private Attributes attributes;

    public FlatButton(Context context) {
        super(context);
        init();
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void onThemeChange() {
        setBackgroundColor(attributes.getColor());
    }

    @Override
    public void init() {
        attributes = new Attributes();
        attributes.setListener(this);
    }

    @Override
    public Attributes getAttributes() {
        return attributes;
    }
}

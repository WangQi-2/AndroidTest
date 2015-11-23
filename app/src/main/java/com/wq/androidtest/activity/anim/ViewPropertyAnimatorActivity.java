package com.wq.androidtest.activity.anim;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/23.
 */
public class ViewPropertyAnimatorActivity extends BaseActivity implements View.OnClickListener {

    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);
        setUpViews();
    }

    private void setUpViews() {
        root = (ViewGroup) findViewById(R.id.root);
        findViewById(R.id.anim).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);

        LayoutTransition l = new LayoutTransition();
        l.enableTransitionType(LayoutTransition.CHANGING);
        root.setLayoutTransition(l);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anim:
                move(v);
                break;
            case R.id.remove:
                remove(v);
                break;
        }
    }

    private void remove(View v) {
        root.removeView(v);
    }

    private void move(View v) {
        v.animate().translationX(400).alpha(0.5f).setDuration(5000);
    }


}

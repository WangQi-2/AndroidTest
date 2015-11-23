package com.wq.androidtest.activity.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.util.Logger;

/**
 * Created by wangqi on 15/11/23.
 */
public class LayoutTransactionActivity extends BaseActivity implements View.OnClickListener {
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouttransaction);
        setUpViews();
    }

    private void setUpViews() {
        findViewById(R.id.remove).setOnClickListener(this);
        root = (ViewGroup) findViewById(R.id.root);
        LayoutTransition l = new LayoutTransition();
        l.enableTransitionType(LayoutTransition.CHANGING);

        l.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Logger.e("startTransition");
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Logger.e("endTransition");
            }
        });

        Animator animator = AnimatorInflater.loadAnimator(mCtx,android.R.animator.fade_in);
        l.setAnimator(LayoutTransition.CHANGING,animator);
        root.setLayoutTransition(l);


        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new GridAdapter());
        grid.setLayoutTransition(l);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remove:
                remove(v);
                break;
        }
    }

    private void remove(View v) {
        root.removeView(v);
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(parent.getContext());
            textView.setText("hey:" + position);
            AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-1,50);
            textView.setLayoutParams(layoutParams);
            return textView;
        }
    }
}

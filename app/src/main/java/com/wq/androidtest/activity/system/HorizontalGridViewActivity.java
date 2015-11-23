package com.wq.androidtest.activity.system;

import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/4.
 */
public class HorizontalGridViewActivity extends BaseActivity {

    HorizontalGridView mGridView;
    MyAdapter mAdapter;
    View preFocusView;
    View curFocusView;

    View borderView;
    FrameLayout rootView;
    AnimatorSet set;
    ViewTreeObserver treeObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalgridview);
        initViews();
        mAdapter = new MyAdapter();
        mGridView.setWindowAlignment(HorizontalGridView.WINDOW_ALIGN_BOTH_EDGE);
        mGridView.setClickable(false);
        mGridView.setFocusableInTouchMode(false);
        mGridView.setFocusable(false);
        mGridView.setWindowAlignmentOffset(350);
        mGridView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mGridView.setAdapter(mAdapter);
        mGridView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    preFocusView = v;
                } else {
                    curFocusView = v;
                    moveFocusView();
                }
            }
        });
        treeObserver = rootView.getViewTreeObserver();
        treeObserver.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                preFocusView = oldFocus;
                curFocusView = newFocus;
                moveFocusView();
            }
        });
    }

    private void initViews() {
        rootView = (FrameLayout) findViewById(R.id.root_view);
        mGridView = (HorizontalGridView) findViewById(R.id.gridview);
        borderView = LayoutInflater.from(this).inflate(R.layout.item_border, rootView).findViewById(R.id.border);
    }


    private void moveFocusView() {
        if (preFocusView == null || curFocusView == null || !(preFocusView instanceof LinearLayout) || !(curFocusView instanceof LinearLayout)) {
            return;
        }

        float preX = preFocusView.getLeft();
        float preY = preFocusView.getTop();
        float curX = curFocusView.getLeft();
        float curY = curFocusView.getTop();
        set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(borderView, "translationX", preX, curX));
        set.playTogether(ObjectAnimator.ofFloat(borderView, "translationY", preY, curY));
        set.setDuration(1000).start();
    }


    class MyAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View root = inflater.inflate(R.layout.item_horizontalgridview_item, null);
            root.setOnHoverListener(new View.OnHoverListener() {
                @Override
                public boolean onHover(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {
                        v.requestFocus();
                    }
                    int offset = 20;
                    float eventX = v.getLeft() + event.getX();
                    int width = mGridView.getWidth();
                    if (lastX != 0) {
                        float delta = lastX - eventX;
                        if (delta > 5 || delta < -5) {
                            if (eventX + offset > width) {
                                mGridView.smoothScrollBy(100, 0);
                            }
                            if (eventX - offset < 0) {
                                mGridView.smoothScrollBy(-100, 0);
                            }
                        }
                    }

                    lastX = eventX;
                    return false;
                }
            });
            return new ViewHolder(root);
        }

        float lastX;

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView.findViewById(R.id.text1);
            textView.setText("position" + position);
//            if (position == 0 && curFocusView == null) {
//                textView.requestFocus();
//                FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(textView.getWidth(),textView.getHeight());
//                borderView.setLayoutParams(p);
//                borderView.setTranslationX(textView.getTranslationX());
//                borderView.setTranslationY(textView.getTranslationX());
//            }
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
        }
    }
}

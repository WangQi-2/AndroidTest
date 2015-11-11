package com.wq.androidtest.activity.system;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.GridLayoutManager;
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
import com.wq.androidtest.activity.BaseActivity;

/**
 * Created by wangqi on 15/11/4.
 */
public class RecyleViewGridViewActivity extends BaseActivity {

    RecyclerView mGridView;
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
        setContentView(R.layout.activity_recyleviewgridview);
        initViews();
        mAdapter = new MyAdapter();
        mGridView.setClickable(false);
        mGridView.setFocusableInTouchMode(false);
        mGridView.setFocusable(false);
        mGridView.addItemDecoration(new DividerGridItemDecoration(this));
        mGridView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
        mGridView.setAdapter(mAdapter);
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

//        float preX = preFocusView.getTranslationX();
//        float preY = preFocusView.getTranslationY();
//        float curX = curFocusView.getTranslationX();
//        float curY = curFocusView.getTranslationY();
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

    public static class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable mDivider;

        public DividerGridItemDecoration(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

            drawHorizontal(c, parent);
            drawVertical(c, parent);

        }

        private int getSpanCount(RecyclerView parent) {
            // 列数
            int spanCount = -1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {

                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                spanCount = ((StaggeredGridLayoutManager) layoutManager)
                        .getSpanCount();
            }
            return spanCount;
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDivider.getIntrinsicWidth();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin;
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicWidth();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        private boolean isLastColum(RecyclerView parent, int pos, int spanCount,
                                    int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                    {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                        return true;
                }
            }
            return false;
        }

        private boolean isLastRaw(RecyclerView parent, int pos, int spanCount,
                                  int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                    return true;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager)
                        .getOrientation();
                // StaggeredGridLayoutManager 且纵向滚动
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    childCount = childCount - childCount % spanCount;
                    // 如果是最后一行，则不需要绘制底部
                    if (pos >= childCount)
                        return true;
                } else
                // StaggeredGridLayoutManager 且横向滚动
                {
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition,
                                   RecyclerView parent) {
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount))// 如果是最后一行，则不需要绘制底部
            {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount))// 如果是最后一列，则不需要绘制右边
            {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(),
                        mDivider.getIntrinsicHeight());
            }
        }
    }
}

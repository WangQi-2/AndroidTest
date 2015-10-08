package com.wq.androidtest.view.group;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wq.androidtest.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/9/13.
 */
public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Logger.e("construt");
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Logger.e("onmeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = child.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if (i == 0) {
                lineHeight = childHeight;
            }

            if (lineWidth + childWidth > sizeWidth) {
                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
        }

        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        }
        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        }
        setMeasuredDimension(width, height);
    }

    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> mLineHeight = new ArrayList<>();

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Logger.e("onlayout");
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineViews = new ArrayList<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width) {
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                lineWidth = 0;
                lineHeight = 0;
                lineViews = new ArrayList<>();
            }

            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int linecount = mAllViews.size();
        int top = 0;
        int left = 0;
        for (int i = 0; i < linecount; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);

            for (int index = 0; index < lineViews.size(); index++) {
                View child = lineViews.get(index);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.rightMargin + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }
    }
}

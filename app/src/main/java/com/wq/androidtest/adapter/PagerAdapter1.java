package com.wq.androidtest.adapter;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wq.androidtest.util.Constant;

/**
 * Created by wangqi on 15/11/13.
 */
public class PagerAdapter1 extends PagerAdapter {


    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView textView = new TextView(container.getContext());
        textView.setText("pos : " + position);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Constant.COLORS[position % Constant.COLORS.length]);

        container.addView(textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewGroup)container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

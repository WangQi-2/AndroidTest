package com.wq.androidtest.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wq.androidtest.util.Constant;

import java.util.HashMap;

/**
 * Created by wangqi on 15/11/13.
 */
public class PagerAdapter1 extends PagerAdapter {

    HashMap<Integer,View> viewMap;


    public PagerAdapter1() {
        viewMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView textView = new TextView(container.getContext());
        textView.setText("pos : " + position);
        textView.setBackgroundColor(Constant.COLORS[position % Constant.COLORS.length]);

        container.addView(textView);
        viewMap.put(position,textView);
        return textView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewMap.get(position));
        viewMap.remove(position);
        super.destroyItem(container, position, object);
    }

//    @Override
//    public void destroyItem(View container, int position, Object object) {
//        ((ViewGroup)container).removeView(viewMap.get(position));
//        super.destroyItem(container, position, object);
//    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

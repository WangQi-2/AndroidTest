package com.wq.androidtest.activity.system;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.adapter.FragmentAdapter;
import com.wq.androidtest.adapter.PagerAdapter1;
import com.wq.androidtest.util.ViewFinder;

/**
 * Created by wangqi on 15/11/13.
 */
public class ViewPagerActivity extends FragmentActivity {
    ViewPager viewPager1;
    ViewPager viewPager2;
    ViewPager viewPager3;
    ViewPager viewPager4;
    ViewPager viewPager5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        View decorView = getWindow().getDecorView();

        viewPager1 = ViewFinder.find(decorView, R.id.viewpager1);
        viewPager2 = ViewFinder.find(decorView, R.id.viewpager2);
        viewPager3 = ViewFinder.find(decorView, R.id.viewpager3);
        viewPager4 = ViewFinder.find(decorView, R.id.viewpager4);
        viewPager5 = ViewFinder.find(decorView, R.id.viewpager5);

        viewPager1.setOffscreenPageLimit(2);
        viewPager1.setPageMargin(10);
        viewPager1.setAdapter(new FragmentAdapter(getSupportFragmentManager(),"fragment"));
        viewPager2.setAdapter(new FragmentAdapter(getSupportFragmentManager(),"fragment"));
        viewPager3.setAdapter(new FragmentAdapter(getSupportFragmentManager(),"fragment"));
        viewPager4.setAdapter(new PagerAdapter1("pageradapter"));
        viewPager5.setAdapter(new PagerAdapter1("loop viewpager"));
    }
}

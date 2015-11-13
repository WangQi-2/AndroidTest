package com.wq.androidtest.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wq.androidtest.fragment.PlaceHolderFragment;
import com.wq.androidtest.util.Constant;

/**
 * Created by wangqi on 15/11/13.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.POSITION,position);
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }
}

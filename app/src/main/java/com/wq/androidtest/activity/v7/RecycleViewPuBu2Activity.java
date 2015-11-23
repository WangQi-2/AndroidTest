package com.wq.androidtest.activity.v7;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.adapter.RecycleAdapter;

/**
 * Created by wangqi on 15/9/14.
 */
public class RecycleViewPuBu2Activity extends BaseActivity{
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(new RecycleAdapter());
    }
}

package com.wq.androidtest.activity.v7;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.adapter.RecycleAdapter;

/**
 * Created by wangqi on 15/9/14.
 */
public class RecycleViewPuBuActivity extends BaseActivity{
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RecycleAdapter());
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_slide_in);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.1f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        recyclerView.setLayoutAnimation(controller);
    }
}

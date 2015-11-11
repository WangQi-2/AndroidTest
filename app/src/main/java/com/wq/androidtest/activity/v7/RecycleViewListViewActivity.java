package com.wq.androidtest.activity.v7;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.adapter.RecycleAdapter;

/**
 * Created by wangqi on 15/9/14.
 */
public class RecycleViewListViewActivity extends BaseActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        btn1 = (Button) findViewById(R.id.btn1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecycleAdapter());
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        recyclerView.setLayoutAnimation(controller);


        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        recyclerView.smoothScrollBy(0,200);
    }
}

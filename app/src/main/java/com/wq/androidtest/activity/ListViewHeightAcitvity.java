package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wq.androidlibrary.util.ViewUtil;
import com.wq.androidtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/9/10.
 */
public class ListViewHeightAcitvity extends BaseActivity implements View.OnClickListener {
    ListView mListView;
    ArrayAdapter<String> adapter;
    Button mBtn;
    Button mBtn1;
    Button mBtn3;
    Button mBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_height);
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < 100; i++) {
            list.add("item " + i);
        }

        mListView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn4 = (Button) findViewById(R.id.btn4);

        mBtn1.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mListView.setLayoutAnimation(controller);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ViewUtil.setListViewHeight(mListView, 300);
                break;
            case R.id.btn1:
                ViewUtil.setListViewHeight(mListView);
                break;
            case R.id.btn3:
                mListView.smoothScrollByOffset(10);
                break;
            case R.id.btn4:
                mListView.smoothScrollToPosition(0);
                break;

        }
    }
}

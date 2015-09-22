package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/9/10.
 */
public class ListViewHeightAcitvity extends BaseActivity implements View.OnClickListener {
    ListView mListView;
    Button mBtn;
    Button mBtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_height);
        List<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item1");
        list.add("item1");
        list.add("item1");
        list.add("item1");
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
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

        }
    }
}

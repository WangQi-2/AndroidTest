package com.wq.androidtest.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.wq.androidtest.R;
import com.wq.androidtest.model.DemoEntryModel;
import com.wq.androidtest.adapter.DemoEntryAdapter;

import java.util.ArrayList;

public class DemoTableActivity extends Activity {

    GridView mDemoEntrys;
    DemoEntryAdapter mAdapter;
    ArrayList<DemoEntryModel> demoEntryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_table);
        mDemoEntrys = (GridView) findViewById(R.id.gv_demo_entry);
        initData();
        mDemoEntrys.setAdapter(null);
    }

    private void initData() {
       demoEntryModels = new ArrayList<>();
//        demoEntryModels.add(new DemoEntryModel("快速入口", ));
    }


}

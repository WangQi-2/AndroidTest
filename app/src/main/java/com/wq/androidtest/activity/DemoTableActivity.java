package com.wq.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.customview.CircleProgressActivity;
import com.wq.androidtest.activity.customview.ProgressButtonActivity;
import com.wq.androidtest.activity.customview.RatingActivity;
import com.wq.androidtest.activity.customview.RiseTextViewActivity;
import com.wq.androidtest.activity.customviewgroup.FlowLayoutActivity;
import com.wq.androidtest.activity.v7.CardViewActivity;
import com.wq.androidtest.activity.v7.RecycleViewGridActivity;
import com.wq.androidtest.activity.v7.RecycleViewListViewActivity;
import com.wq.androidtest.activity.v7.RecycleViewPuBu2Activity;
import com.wq.androidtest.activity.v7.RecycleViewPuBuActivity;
import com.wq.androidtest.adapter.DemoEntryAdapter;
import com.wq.androidtest.model.DemoEntryModel;

import java.util.ArrayList;

public class DemoTableActivity extends BaseActivity {


    GridView mDemoEntrys;
    DemoEntryAdapter mAdapter;
    public static ArrayList<DemoEntryModel> demoEntryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_table);
        mDemoEntrys = (GridView) findViewById(R.id.gv_demo_entry);
        Intent intent = getIntent();
        if (intent == null) {
            initData();
        } else {
            demoEntryModels = (ArrayList<DemoEntryModel>) intent.getSerializableExtra(FUNC_MODELS);
        }
        if (demoEntryModels == null) {
            initData();
        }
        mAdapter = new DemoEntryAdapter(this, demoEntryModels);
        mDemoEntrys.setAdapter(mAdapter);
        mAdapter.getView(0, null, mDemoEntrys).requestFocus();

//        mDemoEntrys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = null;
//                if (demoEntryModels.get(position).getChilds() != null) {
//                    i = new Intent(DemoTableActivity.this, DemoTableActivity.class);
//                    i.putExtra(FUNC_MODELS, (Serializable) demoEntryModels.get(position).getChilds());
//                } else {
//                    i = new Intent(DemoTableActivity.this, demoEntryModels.get(position).getClazz());
//                }
//                i.putExtra(TITLE,demoEntryModels.get(position).getDes());
//                startActivity(i);
//            }
//        });

    }

    private void initData() {
        demoEntryModels = new ArrayList<>();

        //quick access
        demoEntryModels.add(new DemoEntryModel("快速入口", ScreenInfoActivity.class));
        //
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        //custom view
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("ratingbar", RatingActivity.class));
        groupsList.add(new DemoEntryModel("progressbutton", ProgressButtonActivity.class));
        groupsList.add(new DemoEntryModel("circleprogress", CircleProgressActivity.class));
        groupsModel = new DemoEntryModel("***custom views", null, groupsList);
        demoEntryModels.add(groupsModel);
        //custom view group
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("flowlayout", FlowLayoutActivity.class));
        groupsModel = new DemoEntryModel("***custom view groupsList", null, groupsList);
        demoEntryModels.add(groupsModel);
        //v7
        groupsList = new ArrayList<>();


        //sliding
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("outScroll", OutScrollViewActivity.class));
        groupsList.add(new DemoEntryModel("scroll in scroll", ScrollInScrollActivity.class));
        groupsModel = new DemoEntryModel("*** sliding", null, groupsList);
        demoEntryModels.add(groupsModel);

        //single func
        demoEntryModels.add(new DemoEntryModel("test focused", FocusTesetActivity.class));
        demoEntryModels.add(new DemoEntryModel("show ip", ShowIPActivity.class));
        demoEntryModels.add(new DemoEntryModel("eventbus", EventBusDemoActivity.class));
        demoEntryModels.add(new DemoEntryModel("update", UpdateApkActivity.class));
        demoEntryModels.add(new DemoEntryModel("textstyle", TextActivity.class));
        demoEntryModels.add(new DemoEntryModel("mListView height", ListViewHeightAcitvity.class));
        demoEntryModels.add(new DemoEntryModel("animUtilTest", AnimationUtilTestActivity.class));
        demoEntryModels.add(new DemoEntryModel("screeninfo", ScreenInfoActivity.class));
        demoEntryModels.add(new DemoEntryModel("clipboard", ClipboardActivity.class));
        demoEntryModels.add(new DemoEntryModel("ems test", EmsTestActivity.class));
        demoEntryModels.add(new DemoEntryModel("cardview", CardViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("recyclelist", RecycleViewListViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("recyclegrid", RecycleViewGridActivity.class));
        demoEntryModels.add(new DemoEntryModel("recyclepubu", RecycleViewPuBuActivity.class));
        demoEntryModels.add(new DemoEntryModel("recyclepubu2", RecycleViewPuBu2Activity.class));
        demoEntryModels.add(new DemoEntryModel("risetextview", RiseTextViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("enabletest", ViewEnableTestActivity.class));
    }
}

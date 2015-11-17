package com.wq.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.anim.AnimActivity;
import com.wq.androidtest.activity.anim.AnimTestActivity;
import com.wq.androidtest.activity.anim.AnimationSetActivity;
import com.wq.androidtest.activity.customview.BezierActivity;
import com.wq.androidtest.activity.customview.CircleProgressActivity;
import com.wq.androidtest.activity.customview.CircleViewActivity;
import com.wq.androidtest.activity.customview.FontMetricsActivity;
import com.wq.androidtest.activity.customview.HorizontalScrollExActivity;
import com.wq.androidtest.activity.customview.ProgressButtonActivity;
import com.wq.androidtest.activity.customview.RatingActivity;
import com.wq.androidtest.activity.customview.RiseTextViewActivity;
import com.wq.androidtest.activity.customviewgroup.FlowLayoutActivity;
import com.wq.androidtest.activity.system.HorizontalGridViewActivity;
import com.wq.androidtest.activity.system.RecyleViewGridViewActivity;
import com.wq.androidtest.activity.system.ViewPagerActivity;
import com.wq.androidtest.activity.system.VolumeActivity;
import com.wq.androidtest.activity.v7.CardViewActivity;
import com.wq.androidtest.activity.v7.RecycleViewGridActivity;
import com.wq.androidtest.activity.v7.RecycleViewListViewActivity;
import com.wq.androidtest.activity.v7.RecycleViewPuBu2Activity;
import com.wq.androidtest.activity.v7.RecycleViewPuBuActivity;
import com.wq.androidtest.adapter.DemoEntryAdapter;
import com.wq.androidtest.model.DemoEntryModel;

import java.io.Serializable;
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
//        mAdapter.getView(0, null, mDemoEntrys).requestFocus();

        mDemoEntrys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(mCtx,"item clicked");
                Intent i = null;
                if (demoEntryModels.get(position).getChilds() != null) {
                    i = new Intent(DemoTableActivity.this, DemoTableActivity.class);
                    i.putExtra(FUNC_MODELS, (Serializable) demoEntryModels.get(position).getChilds());
                } else {
                    i = new Intent(DemoTableActivity.this, demoEntryModels.get(position).getClazz());
                }
                i.putExtra(TITLE,demoEntryModels.get(position).getDes());
                startActivity(i);
            }
        });

    }

    private void initData() {
        demoEntryModels = new ArrayList<>();

        //quick access
        demoEntryModels.add(new DemoEntryModel("快速入口", ViewPagerActivity.class));
        //
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        //custom view
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("ratingbar", RatingActivity.class));
        groupsList.add(new DemoEntryModel("circleview", CircleViewActivity.class));
        groupsList.add(new DemoEntryModel("progressbutton", ProgressButtonActivity.class));
        groupsList.add(new DemoEntryModel("circleprogress", CircleProgressActivity.class));
        groupsModel = new DemoEntryModel("*views", null, groupsList);
        demoEntryModels.add(groupsModel);
        //custom view group
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("horizontalscorllex", HorizontalScrollExActivity.class));
        groupsList.add(new DemoEntryModel("flowlayout", FlowLayoutActivity.class));
        groupsList.add(new DemoEntryModel("ripple", RippleLayoutActivity.class));
        groupsModel = new DemoEntryModel("*viewgroups", null, groupsList);
        demoEntryModels.add(groupsModel);
        //anim
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("layoutanim", LayoutAnimationActivity.class));
        groupsList.add(new DemoEntryModel("animationset", AnimationSetActivity.class));
        groupsModel = new DemoEntryModel("*anim", null, groupsList);
        demoEntryModels.add(groupsModel);
        //orignal
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("cardview", CardViewActivity.class));
        groupsList.add(new DemoEntryModel("recyclelist", RecycleViewListViewActivity.class));
        groupsList.add(new DemoEntryModel("recyclegrid", RecycleViewGridActivity.class));
        groupsList.add(new DemoEntryModel("recyclepubu", RecycleViewPuBuActivity.class));
        groupsList.add(new DemoEntryModel("recyclepubu2", RecycleViewPuBu2Activity.class));
        groupsList.add(new DemoEntryModel("switch", SwitchActivity.class));
        groupsList.add(new DemoEntryModel("textstyle", TextActivity.class));
        groupsList.add(new DemoEntryModel("test focused", FocusTesetActivity.class));
        groupsList.add(new DemoEntryModel("clipboard", ClipboardActivity.class));
        groupsList.add(new DemoEntryModel("screeninfo", ScreenInfoActivity.class));
        groupsList.add(new DemoEntryModel("ems test", EmsTestActivity.class));
        groupsList.add(new DemoEntryModel("deviceInfo", DeviceInfoActivity.class));
        groupsModel = new DemoEntryModel("*orignal", null, groupsList);
        demoEntryModels.add(groupsModel);

        //scroll
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("outScroll", OutScrollViewActivity.class));
        groupsList.add(new DemoEntryModel("scroll in scroll", ScrollInScrollActivity.class));
        groupsModel = new DemoEntryModel("*scrollview", null, groupsList);
        demoEntryModels.add(groupsModel);

        //single func
        demoEntryModels.add(new DemoEntryModel("show ip", ShowIPActivity.class));
        demoEntryModels.add(new DemoEntryModel("eventbus", EventBusDemoActivity.class));
        demoEntryModels.add(new DemoEntryModel("update", UpdateApkActivity.class));
        demoEntryModels.add(new DemoEntryModel("mListView height", ListViewHeightAcitvity.class));
        demoEntryModels.add(new DemoEntryModel("animUtilTest", AnimationUtilTestActivity.class));
        demoEntryModels.add(new DemoEntryModel("risetextview", RiseTextViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("enabletest", ViewEnableTestActivity.class));
        demoEntryModels.add(new DemoEntryModel("view trans", ViewTransActivity.class));
        demoEntryModels.add(new DemoEntryModel("notification", NotificationActivity.class));
        demoEntryModels.add(new DemoEntryModel("anim", AnimActivity.class));
        demoEntryModels.add(new DemoEntryModel("number clock", FontMetricsActivity.class));
        demoEntryModels.add(new DemoEntryModel("imagesize", ImageSizeActivity.class));
        demoEntryModels.add(new DemoEntryModel("customDrawable", CustomDrawableActivity.class));
        demoEntryModels.add(new DemoEntryModel("blurDialog", BlurDialogActivity.class));
        demoEntryModels.add(new DemoEntryModel("back", FakeExitActivity.class));
        demoEntryModels.add(new DemoEntryModel("fbutton", FButtonActivity.class));
        demoEntryModels.add(new DemoEntryModel("flatUI", FlatUIActivity.class));
        demoEntryModels.add(new DemoEntryModel("animtest", AnimTestActivity.class));
        demoEntryModels.add(new DemoEntryModel("cube ainm",CubeAnimActivity.class));
        demoEntryModels.add(new DemoEntryModel("bezier",BezierActivity.class));
        demoEntryModels.add(new DemoEntryModel("floatingview",FloatingWindowActivity.class));
        demoEntryModels.add(new DemoEntryModel("test speed",ShowNetWorkSpeedActivity.class));
        demoEntryModels.add(new DemoEntryModel("test volume",VolumeActivity.class));
        demoEntryModels.add(new DemoEntryModel("horizontalgridview",HorizontalGridViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("recyleViewGridview",RecyleViewGridViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("test scrollview",ScrollViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("viewpager",ViewPagerActivity.class));
    }
}

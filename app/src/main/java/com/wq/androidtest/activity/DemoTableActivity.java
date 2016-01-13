package com.wq.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.anim.ActivityOptionActivity;
import com.wq.androidtest.activity.anim.AnimActivity;
import com.wq.androidtest.activity.anim.AnimTweenActivity;
import com.wq.androidtest.activity.anim.AnimationSetActivity;
import com.wq.androidtest.activity.anim.CardFlipFragmentAcitivity;
import com.wq.androidtest.activity.anim.CardFlipViewActivity;
import com.wq.androidtest.activity.anim.CrossFadeActivity;
import com.wq.androidtest.activity.anim.CubeAnimActivity;
import com.wq.androidtest.activity.anim.FlipActivity;
import com.wq.androidtest.activity.anim.LayoutAnimationActivity;
import com.wq.androidtest.activity.anim.LayoutTransactionActivity;
import com.wq.androidtest.activity.anim.MoreFlipActivity;
import com.wq.androidtest.activity.anim.RGBAnimActivity;
import com.wq.androidtest.activity.anim.ViewCube2Activity;
import com.wq.androidtest.activity.anim.ViewCubeActivity;
import com.wq.androidtest.activity.anim.ViewPropertyAnimatorActivity;
import com.wq.androidtest.activity.anim.ZoomActivity;
import com.wq.androidtest.activity.base.BaseActivity;
import com.wq.androidtest.activity.customview.BezierActivity;
import com.wq.androidtest.activity.customview.CircleProgressActivity;
import com.wq.androidtest.activity.customview.CircleViewActivity;
import com.wq.androidtest.activity.customview.CustomDrawableActivity;
import com.wq.androidtest.activity.customview.FontMetricsActivity;
import com.wq.androidtest.activity.customview.GifViewActivity;
import com.wq.androidtest.activity.customview.HorizontalScrollExActivity;
import com.wq.androidtest.activity.customview.IndicatorActivity;
import com.wq.androidtest.activity.customview.ProgressButtonActivity;
import com.wq.androidtest.activity.customview.RatingActivity;
import com.wq.androidtest.activity.customview.RippleLayoutActivity;
import com.wq.androidtest.activity.customview.RiseTextViewActivity;
import com.wq.androidtest.activity.customview.SunAndMoonActivity;
import com.wq.androidtest.activity.customview.TickViewActivity;
import com.wq.androidtest.activity.customviewgroup.FlowLayoutActivity;
import com.wq.androidtest.activity.customviewgroup.ListViewHeightAcitvity;
import com.wq.androidtest.activity.customviewgroup.OutScrollViewActivity;
import com.wq.androidtest.activity.customviewgroup.ScrollInScrollActivity;
import com.wq.androidtest.activity.customviewgroup.ScrollViewActivity;
import com.wq.androidtest.activity.framework.EventBusDemoActivity;
import com.wq.androidtest.activity.framework.FButtonActivity;
import com.wq.androidtest.activity.framework.FlatUIActivity;
import com.wq.androidtest.activity.function.AnimationUtilTestActivity;
import com.wq.androidtest.activity.function.BlurDialogActivity;
import com.wq.androidtest.activity.function.ClipboardActivity;
import com.wq.androidtest.activity.function.FakeExitActivity;
import com.wq.androidtest.activity.function.FloatingWindowActivity;
import com.wq.androidtest.activity.function.NotificationActivity;
import com.wq.androidtest.activity.function.ShowNetWorkSpeedActivity;
import com.wq.androidtest.activity.function.UpdateApkActivity;
import com.wq.androidtest.activity.others.SwitchActivity;
import com.wq.androidtest.activity.system.BitmapDrawableActivity;
import com.wq.androidtest.activity.system.DeviceInfoActivity;
import com.wq.androidtest.activity.system.EmsTestActivity;
import com.wq.androidtest.activity.system.FocusTesetActivity;
import com.wq.androidtest.activity.system.HorizontalGridViewActivity;
import com.wq.androidtest.activity.system.ImageSizeActivity;
import com.wq.androidtest.activity.system.IntentActivity;
import com.wq.androidtest.activity.system.RecyleViewGridViewActivity;
import com.wq.androidtest.activity.system.ScreenInfoActivity;
import com.wq.androidtest.activity.system.SeekBarActivity;
import com.wq.androidtest.activity.system.ShowIPActivity;
import com.wq.androidtest.activity.system.TextActivity;
import com.wq.androidtest.activity.system.ViewEnableTestActivity;
import com.wq.androidtest.activity.system.ViewPagerActivity;
import com.wq.androidtest.activity.system.ViewTransActivity;
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

    private void addSingleItems() {
        demoEntryModels.add(new DemoEntryModel("test scrollview", ScrollViewActivity.class));
        demoEntryModels.add(new DemoEntryModel("mToolbar", ToolbarActivity.class));
        demoEntryModels.add(new DemoEntryModel("drawer", DrawerLayoutActivity.class));
        demoEntryModels.add(new DemoEntryModel("moreflip", MoreFlipActivity.class));
    }

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

        mDemoEntrys.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                if (demoEntryModels.get(position).getChilds() != null) {
                    i = new Intent(DemoTableActivity.this, DemoTableActivity.class);
                    i.putExtra(FUNC_MODELS, (Serializable) demoEntryModels.get(position).getChilds());
                } else {
                    i = new Intent(DemoTableActivity.this, demoEntryModels.get(position).getClazz());
                }
                i.putExtra(TITLE, demoEntryModels.get(position).getDes());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                startActivity(i, optionsCompat.toBundle());
            }
        });
    }

    private void initData() {
        demoEntryModels = new ArrayList<>();

        //quick access
        demoEntryModels.add(new DemoEntryModel("快速入口", ViewCube2Activity.class));
        //
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        //custom view
        addCustomViewItems();
        //custom view group
        addCustomViewGroupItems();
        //anim
        addAnimItems();
        //system
        addSystemItems();
        //view
        addViewItems();
        //viewgroup
        addViewGroupItems();
        //framework
        addFramework();
        //functions
        addFunctionItems();
        //single
        addSingleItems();
    }

    private void addViewItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsModel = new DemoEntryModel("*view", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addFunctionItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("back", FakeExitActivity.class));
        groupsList.add(new DemoEntryModel("bezier", BezierActivity.class));
        groupsList.add(new DemoEntryModel("update", UpdateApkActivity.class));
        groupsList.add(new DemoEntryModel("blurDialog", BlurDialogActivity.class));
        groupsList.add(new DemoEntryModel("notification", NotificationActivity.class));
        groupsList.add(new DemoEntryModel("view trans", ViewTransActivity.class));
        groupsList.add(new DemoEntryModel("test speed", ShowNetWorkSpeedActivity.class));
        groupsList.add(new DemoEntryModel("floatingview", FloatingWindowActivity.class));
        groupsList.add(new DemoEntryModel("imagesize", ImageSizeActivity.class));
        groupsModel = new DemoEntryModel("*function", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addFramework() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("flatUI", FlatUIActivity.class));
        groupsList.add(new DemoEntryModel("fbutton", FButtonActivity.class));
        groupsList.add(new DemoEntryModel("eventbus", EventBusDemoActivity.class));
        groupsModel = new DemoEntryModel("*framework", null, groupsList);
        demoEntryModels.add(groupsModel);
    }


    private void addViewGroupItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("viewpager", ViewPagerActivity.class));
        groupsList.add(new DemoEntryModel("seekbar", SeekBarActivity.class));
        groupsList.add(new DemoEntryModel("mListView height", ListViewHeightAcitvity.class));
        groupsList.add(new DemoEntryModel("outScroll", OutScrollViewActivity.class));
        groupsList.add(new DemoEntryModel("scroll in scroll", ScrollInScrollActivity.class));
        groupsModel = new DemoEntryModel("*scrollview", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addSystemItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("show ip", ShowIPActivity.class));
        groupsList.add(new DemoEntryModel("cardview", CardViewActivity.class));
        groupsList.add(new DemoEntryModel("intent", IntentActivity.class));
        groupsList.add(new DemoEntryModel("recyclelist", RecycleViewListViewActivity.class));
        groupsList.add(new DemoEntryModel("recyclegrid", RecycleViewGridActivity.class));
        groupsList.add(new DemoEntryModel("recyclepubu", RecycleViewPuBuActivity.class));
        groupsList.add(new DemoEntryModel("enabletest", ViewEnableTestActivity.class));
        groupsList.add(new DemoEntryModel("recyclepubu2", RecycleViewPuBu2Activity.class));
        groupsList.add(new DemoEntryModel("switch", SwitchActivity.class));
        groupsList.add(new DemoEntryModel("textstyle", TextActivity.class));
        groupsList.add(new DemoEntryModel("test focused", FocusTesetActivity.class));
        groupsList.add(new DemoEntryModel("clipboard", ClipboardActivity.class));
        groupsList.add(new DemoEntryModel("screeninfo", ScreenInfoActivity.class));
        groupsList.add(new DemoEntryModel("ems test", EmsTestActivity.class));
        groupsList.add(new DemoEntryModel("deviceInfo", DeviceInfoActivity.class));
        groupsList.add(new DemoEntryModel("test volume", VolumeActivity.class));
        groupsList.add(new DemoEntryModel("bitmap drawable", BitmapDrawableActivity.class));
        groupsModel = new DemoEntryModel("*system", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addAnimItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("layoutanim", LayoutAnimationActivity.class));
        groupsList.add(new DemoEntryModel("anim", AnimActivity.class));
        groupsList.add(new DemoEntryModel("view cube", ViewCubeActivity.class));
        groupsList.add(new DemoEntryModel("cube ainm", CubeAnimActivity.class));
        groupsList.add(new DemoEntryModel("ActivityOption", ActivityOptionActivity.class));
        groupsList.add(new DemoEntryModel("tween anim", AnimTweenActivity.class));
        groupsList.add(new DemoEntryModel("animUtilTest", AnimationUtilTestActivity.class));
        groupsList.add(new DemoEntryModel("animationset", AnimationSetActivity.class));
        groupsList.add(new DemoEntryModel("layouttransition", LayoutTransactionActivity.class));
        groupsList.add(new DemoEntryModel("viewPropertyAnimator", ViewPropertyAnimatorActivity.class));
        groupsList.add(new DemoEntryModel("crossfade", CrossFadeActivity.class));
        groupsList.add(new DemoEntryModel("flipview", CardFlipViewActivity.class));
        groupsList.add(new DemoEntryModel("zoom", ZoomActivity.class));
        groupsList.add(new DemoEntryModel("flipFragment", CardFlipFragmentAcitivity.class));
        groupsList.add(new DemoEntryModel("RGBAnim", RGBAnimActivity.class));
        groupsList.add(new DemoEntryModel("flipanim", FlipActivity.class));
        groupsModel = new DemoEntryModel("*anim", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addCustomViewGroupItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("horizontalscorllex", HorizontalScrollExActivity.class));
        groupsList.add(new DemoEntryModel("flowlayout", FlowLayoutActivity.class));
        groupsList.add(new DemoEntryModel("tick", TickViewActivity.class));
        groupsList.add(new DemoEntryModel("horizontalgridview", HorizontalGridViewActivity.class));
        groupsList.add(new DemoEntryModel("recyleViewGridview", RecyleViewGridViewActivity.class));
        groupsList.add(new DemoEntryModel("ripple", RippleLayoutActivity.class));
        groupsModel = new DemoEntryModel("*viewgroups", null, groupsList);
        demoEntryModels.add(groupsModel);
    }

    private void addCustomViewItems() {
        ArrayList<DemoEntryModel> groupsList;
        DemoEntryModel groupsModel;
        groupsList = new ArrayList<>();
        groupsList.add(new DemoEntryModel("ratingbar", RatingActivity.class));
        groupsList.add(new DemoEntryModel("circleview", CircleViewActivity.class));
        groupsList.add(new DemoEntryModel("progressbutton", ProgressButtonActivity.class));
        groupsList.add(new DemoEntryModel("circleprogress", CircleProgressActivity.class));
        groupsList.add(new DemoEntryModel("indicator", IndicatorActivity.class));
        groupsList.add(new DemoEntryModel("number clock", FontMetricsActivity.class));
        groupsList.add(new DemoEntryModel("sun moon", SunAndMoonActivity.class));
        groupsList.add(new DemoEntryModel("risetextview", RiseTextViewActivity.class));
        groupsList.add(new DemoEntryModel("gif view", GifViewActivity.class));
        groupsList.add(new DemoEntryModel("customDrawable", CustomDrawableActivity.class));
        groupsModel = new DemoEntryModel("*views", null, groupsList);
        demoEntryModels.add(groupsModel);
    }
}

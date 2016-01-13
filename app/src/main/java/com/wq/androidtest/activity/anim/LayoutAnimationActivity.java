package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/28.
 */
public class LayoutAnimationActivity extends BaseActivity {

    public static final float DURATION1 = 0.05f;
    public static final float DURATION2 = 0.05f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        //
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.verticalLL);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_top_in);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION1);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);
        //
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLL1);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION2);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);
        //
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLL2);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION2 * 2);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);
        //
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLL3);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION2 * 3);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);
        //
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLL4);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION2 * 4);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);
        //
        linearLayout = (LinearLayout) findViewById(R.id.horizontalLL5);
        animation = AnimationUtils.loadAnimation(this, R.anim.slide_left_in);
        controller = new LayoutAnimationController(animation);
        controller.setDelay(DURATION2 * 5);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        linearLayout.setLayoutAnimation(controller);


    }
}

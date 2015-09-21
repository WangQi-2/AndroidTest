package com.wq.androidtest.activity.anim;

import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.activity.DemoTableActivity;
import com.wq.androidtest.activity.v7.RecycleViewListViewActivity;
import com.wq.androidtest.util.ToastUtil;

/**
 * Created by wangqi on 15/9/21.
 */
public class AnimActivity extends BaseActivity {

    Button showActivityBtn;
    Button showBtn;
    Button showLayoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        showActivityBtn = (Button) findViewById(R.id.showActivityBtn);
        showBtn = (Button) findViewById(R.id.showButton);
        showLayoutBtn = (Button) findViewById(R.id.showLayoutBtn);
    }

    public void showActivity(View view) {
        Intent intent = new Intent(this, DemoTableActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void showButton(View view) {
        ToastUtil.showToast(this, "showButton");
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator evaluator = new IntEvaluator();

            //// TODO: 15/9/21 这个sdk版本要求比较高啊
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentvalue = (int) valueAnimator.getAnimatedValue();
                float fraction = valueAnimator.getAnimatedFraction();
                showBtn.getLayoutParams().width = evaluator.evaluate(fraction, 1, 800);
                showBtn.requestLayout();
            }
        });
        valueAnimator.setDuration(4000).start();
    }

    public void showLayout(View view) {
        Intent intent = new Intent(this, RecycleViewListViewActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}

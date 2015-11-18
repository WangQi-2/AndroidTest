package com.wq.androidtest.activity.anim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.util.Constant;
import com.wq.androidtest.util.ViewFinder;

/**
 * Created by wangqi on 15/11/17.
 */
public class AnimationSetActivity extends BaseActivity implements View.OnClickListener {
    Button button9;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationset);
        setUpViews();
    }

    private void setUpViews() {
        imageView = ViewFinder.find(decorView, R.id.imageView);
        imageView2 = ViewFinder.find(decorView, R.id.imageView2);
        imageView3 = ViewFinder.find(decorView, R.id.imageView3);
        imageView4 = ViewFinder.find(decorView, R.id.imageView4);
        imageView5 = ViewFinder.find(decorView, R.id.imageView5);
        imageView6 = ViewFinder.find(decorView, R.id.imageView6);
        imageView7 = ViewFinder.find(decorView, R.id.imageView7);
        button9 = ViewFinder.find(decorView, R.id.button9);
        button9.setOnClickListener(this);

        imageView.setAlpha(0f);
        imageView2.setAlpha(0f);
        imageView3.setAlpha(0f);
        imageView4.setAlpha(0f);
        imageView5.setAlpha(0f);
        imageView6.setAlpha(0f);
        imageView7.setAlpha(0f);
    }

    @Override
    public void onClick(View v) {
        startAnim();
    }

    private void startAnim() {
        ObjectAnimator anim = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(imageView2, "alpha", 0f, 1f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(imageView3, "alpha", 0f, 1f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(imageView4, "alpha", 0f, 1f);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(imageView5, "alpha", 0f, 1f);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(imageView6, "alpha", 0f, 1f);
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(imageView7, "alpha", 0f, 1f);

        anim.setDuration(Constant.DURATION);
        anim2.setDuration(Constant.DURATION);
        anim3.setDuration(Constant.DURATION);
        anim4.setDuration(Constant.DURATION);
        anim5.setDuration(Constant.DURATION);
        anim6.setDuration(Constant.DURATION);
        anim7.setDuration(Constant.DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        anim2.setStartDelay(50);
        anim3.setStartDelay(250);
        anim4.setStartDelay(450);
        anim5.setStartDelay(650);
        anim6.setStartDelay(850);
        anim7.setStartDelay(1050);

        animatorSet.play(anim)
                .with(anim2)
                .with(anim3)
                .with(anim4)
                .with(anim5)
                .with(anim6)
                .with(anim7);
        animatorSet.start();
    }
}

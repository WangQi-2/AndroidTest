package com.wq.androidtest.activity.system;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangqi on 15/12/9.
 */
public class ViewCubeActivity extends BaseActivity {

    private View view1;
    private View view2;
    private View view3;
    private View view4;
    private static float SCREEN_WIDTH = 1080;
    private static float VIEW_WIDTH = SCREEN_WIDTH / 2;
    private static int DURATION = 2000;
    private static float DEGREE_SMALL = 30;
    private static float DEGREE_BIG = 62.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        setUpViews();
    }

    private void setUpViews() {
        view1 = findViewById(R.id.image1);
        view2 = findViewById(R.id.image2);
        view3 = findViewById(R.id.image3);
        view4 = findViewById(R.id.image4);

        view1.setPivotY(VIEW_WIDTH / 2);
        view2.setPivotY(VIEW_WIDTH / 2);
        view3.setPivotY(VIEW_WIDTH / 2);
        view4.setPivotY(VIEW_WIDTH / 2);

        view1.setPivotX(VIEW_WIDTH);
        view1.setTranslationX(-VIEW_WIDTH);
        view1.setRotationY(-DEGREE_SMALL);

        view3.setPivotX(0);
        view3.setTranslationX(VIEW_WIDTH);
        view3.setRotationY(DEGREE_BIG);

        view4.setPivotX(VIEW_WIDTH);
        view4.setTranslationX(VIEW_WIDTH / 2);
        view4.setRotationY(-DEGREE_BIG);
    }

    public void click(View v) {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(makeLeft2DispearAnimator(view1));
        animators.addAll(makeCenter2LeftAnimator(view2));
        animators.addAll(makeRightOne2CenterAnimator(view3));
        animators.addAll(makeRightTwo2RightAnimator(view4));
        set.playTogether(animators);
        set.start();

    }

    private Collection<Animator> makeLeft2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, -(VIEW_WIDTH / 2 + VIEW_WIDTH));
        ObjectAnimator routationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, 0.635f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, 0.635f);
        animators.add(translationX);
        animators.add(routationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> makeCenter2LeftAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, -VIEW_WIDTH);
        ObjectAnimator routationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -DEGREE_SMALL);
        animators.add(translationX);
        animators.add(routationY);
        return animators;
    }

    private Collection<Animator> makeRightOne2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, 0);
        ObjectAnimator routationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_BIG, 0);
        animators.add(translationX);
        animators.add(routationY);
        return animators;
    }

    private Collection<Animator> makeRightTwo2RightAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH / 2, VIEW_WIDTH * 0.86f);
        ObjectAnimator routationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_BIG, DEGREE_SMALL);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, 0.68f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, 0.68f);
        animators.add(translationX);
        animators.add(routationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }


}

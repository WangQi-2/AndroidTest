package com.wq.androidtest.activity.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.wq.androidtest.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by wangqi on 15/12/11.
 */
public class ViewCube2Activity extends Activity implements View.OnClickListener {


    private static final float SCREEN_WIDTH = 1920;
    private static final float VIEW_WIDTH = 1392;
    private static final float SIDE_WIDTH = (SCREEN_WIDTH - VIEW_WIDTH) / 2;
    private static final float VIEW_HEIGHT = 700;
    private static final int DURATION = 150;
    private static final float DEGREE_SMALL = 20;
    private static final float DEGREE_BIG = 70;
    private static final float DISPEAR_SCALE_RATE = 0.305f;
    private static final float VIEW4_MOVING_RATE = 1.105f;

    private LinearLayout viewGroup1;
    private LinearLayout viewGroup2;
    private LinearLayout viewGroup3;
    private LinearLayout viewGroup4;
    private LinearLayout viewGroup5;
    private LinearLayout viewGroup6;

    private Button next1;
    private Button next2;
    private Button next3;
    private Button next4;
    private Button next5;

    private Button back2;
    private Button back3;
    private Button back4;
    private Button back5;
    private Button back6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cube2);
        setUpViews();
    }

    private void setUpViews() {

        //find views
        viewGroup1 = (LinearLayout) findViewById(R.id.viewgroup1);
        viewGroup2 = (LinearLayout) findViewById(R.id.viewgroup2);
        viewGroup3 = (LinearLayout) findViewById(R.id.viewgroup3);
        viewGroup4 = (LinearLayout) findViewById(R.id.viewgroup4);
        viewGroup5 = (LinearLayout) findViewById(R.id.viewgroup5);
        viewGroup6 = (LinearLayout) findViewById(R.id.viewgroup6);

        viewGroup1.setBackgroundColor(0xffff0000);
        viewGroup2.setBackgroundColor(0xff0000ff);
        viewGroup3.setBackgroundColor(0xffff00ff);
        viewGroup4.setBackgroundColor(0xffffff00);
        viewGroup5.setBackgroundColor(0xff00ff00);
        viewGroup6.setBackgroundColor(0xff00ffff);

        next1 = (Button) findViewById(R.id.nextbtn1);
        next2 = (Button) findViewById(R.id.nextbtn2);
        next3 = (Button) findViewById(R.id.nextbtn3);
        next4 = (Button) findViewById(R.id.nextbtn4);
        next5 = (Button) findViewById(R.id.nextbtn5);

        back2 = (Button) findViewById(R.id.backbtn2);
        back3 = (Button) findViewById(R.id.backbtn3);
        back4 = (Button) findViewById(R.id.backbtn4);
        back5 = (Button) findViewById(R.id.backbtn5);
        back6 = (Button) findViewById(R.id.backbtn6);

        //set on listeners
        next1.setOnClickListener(this);
        next2.setOnClickListener(this);
        next3.setOnClickListener(this);
        next4.setOnClickListener(this);
        next5.setOnClickListener(this);

        back2.setOnClickListener(this);
        back3.setOnClickListener(this);
        back4.setOnClickListener(this);
        back5.setOnClickListener(this);
        back6.setOnClickListener(this);

        viewGroup1.setPivotY(VIEW_HEIGHT / 2);
        viewGroup2.setPivotY(VIEW_HEIGHT / 2);
        viewGroup3.setPivotY(VIEW_HEIGHT / 2);
        viewGroup4.setPivotY(VIEW_HEIGHT / 2);
        viewGroup5.setPivotY(VIEW_HEIGHT / 2);
        viewGroup6.setPivotY(VIEW_HEIGHT / 2);

        //init views position
        viewGroup2.setPivotX(0);
        viewGroup2.setTranslationX(VIEW_WIDTH);
        viewGroup2.setRotationY(DEGREE_SMALL);
        viewGroup3.setTranslationX(VIEW_WIDTH + SIDE_WIDTH);
        viewGroup4.setTranslationX(VIEW_WIDTH + SIDE_WIDTH);
        viewGroup5.setTranslationX(VIEW_WIDTH + SIDE_WIDTH);
        viewGroup6.setTranslationX(VIEW_WIDTH + SIDE_WIDTH);

        chooseGroup1();
    }

    private void next2Step2() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateCenter2LeftOneAnimator(viewGroup1));
        animators.addAll(generateRightOne2CenterAnimator(viewGroup2));
        animators.addAll(generateDispear2RightTwoAnimator(viewGroup3));
        set.playTogether(animators);
        set.start();

        chooseGroup2();
    }

    private void next2Step3() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftOne2DispearAnimator(viewGroup1));
        animators.addAll(generateCenter2LeftTwoAnimator(viewGroup2));
        animators.addAll(generateRightTwo2CenterAnimator(viewGroup3));
        animators.addAll(generateDispear2RightThreeAnimator(viewGroup4));
        animators.addAll(generateDispear2RightFourAnimator(viewGroup5));
        set.playTogether(animators);
        set.start();

        chooseGroup3();
    }

    private void next2Step4() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftTwo2LeftFourAnimator(viewGroup2));
        animators.addAll(generateCenter2LeftThreeAnimator(viewGroup3));
        animators.addAll(generateRightThree2CenterAnimator(viewGroup4));
        animators.addAll(generateRightFour2RightTwoAnimator(viewGroup5));
        set.playTogether(animators);
        set.start();

        chooseGroup4();
    }

    private void next2Step5() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftFour2DispearAnimator(viewGroup2));
        animators.addAll(generateLeftThree2DispearAnimator(viewGroup3));
        animators.addAll(generateCenter2LeftTwoAnimator(viewGroup4));
        animators.addAll(generateRightTwo2CenterAnimator(viewGroup5));
        animators.addAll(generateDispear2RightTwoAnimator(viewGroup6));
        set.playTogether(animators);
        set.start();

        chooseGroup5();
    }

    private void next2Step6() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftTwo2DispearAnimator(viewGroup4));
        animators.addAll(generateCenter2LeftTwoAnimator(viewGroup5));
        animators.addAll(generateRightTwo2CenterAnimator(viewGroup6));
        set.playTogether(animators);
        set.start();

        chooseGroup6();
    }

    private void back2Step1() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftOne2CenterAnimator(viewGroup1));
        animators.addAll(generateCenter2RightOneAnimator(viewGroup2));
        animators.addAll(generateRightTwo2DispearAnimator(viewGroup3));
        set.playTogether(animators);
        set.start();

        chooseGroup1();
    }

    private void back2Step2() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateDispear2LeftOneAnimator(viewGroup1));
        animators.addAll(generateLeftOne2CenterAnimator(viewGroup2));
        animators.addAll(generateCenter2RightTwoAnimator(viewGroup3));
        animators.addAll(generateRightThree2DispearAnimator(viewGroup4));
        animators.addAll(generateRightFour2DispearAnimator(viewGroup5));
        set.playTogether(animators);
        set.start();

        chooseGroup2();
    }

    private void back2Step3() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateLeftFour2LeftTwoAnimator(viewGroup2));
        animators.addAll(generateLeftThree2CenterAnimator(viewGroup3));
        animators.addAll(generateCenter2RightThreeAnimator(viewGroup4));
        animators.addAll(generateRightTwo2RightFourAnimator(viewGroup5));
        set.playTogether(animators);
        set.start();

        chooseGroup3();
    }

    private void back2Step4() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateRightTwo2DispearAnimator(viewGroup6));
        animators.addAll(generateCenter2RightTwoAnimator(viewGroup5));
        animators.addAll(generateLeftTwo2CenterAnimator(viewGroup4));
        animators.addAll(generateDispear2LeftThreeAnimator(viewGroup3));
        animators.addAll(generateDispear2LeftFourAnimator(viewGroup2));
        set.playTogether(animators);
        set.start();

        chooseGroup4();
    }

    private void back2Step5() {
        AnimatorSet set = new AnimatorSet();
        set.setDuration(DURATION);
        List<Animator> animators = new ArrayList<>();
        animators.addAll(generateCenter2RightTwoAnimator(viewGroup6));
        animators.addAll(generateLeftTwo2CenterAnimator(viewGroup5));
        animators.addAll(generateDispear2LeftTwoAnimator(viewGroup4));
        set.playTogether(animators);
        set.start();

        chooseGroup5();
    }


    private Collection<Animator> generateCenter2LeftOneAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -DEGREE_SMALL);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateCenter2LeftTwoAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, DEGREE_SMALL);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateLeftTwo2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_SMALL, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateLeftOne2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2RightTwoAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH * 1.5f, VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -DEGREE_SMALL);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateRightTwo2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, VIEW_WIDTH * 1.5f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(scaleX);
        animators.add(scaleY);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateLeftTwo2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, -VIEW_WIDTH * 1.5f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(scaleX);
        animators.add(scaleY);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateRightTwo2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateCenter2RightTwoAnimator(View v) {
        v.setPivotX(0);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -DEGREE_SMALL);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }


    private Collection<Animator> generateLeftOne2DispearAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, -VIEW_WIDTH * 1.5f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_SMALL, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2LeftOneAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * 1.5f, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, DEGREE_SMALL);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2LeftTwoAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * 1.5f, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, DEGREE_SMALL);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateRightOne2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_SMALL, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateCenter2RightOneAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, DEGREE_SMALL);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2RightThreeAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH * 1.5f, VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, 5, 15, 25, DEGREE_BIG);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2LeftThreeAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * 1.5f, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -5, -15, -25, -DEGREE_BIG);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateRightThree2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, VIEW_WIDTH * 1.5f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_BIG, 25, 15, 5, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateLeftThree2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(VIEW_WIDTH);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, -VIEW_WIDTH * 1.5f);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_BIG, -25, -15, -5, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateRightThree2CenterAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_BIG, 25, 15, 5, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateCenter2RightThreeAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, 5, 15, 25, DEGREE_BIG);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }

    private Collection<Animator> generateDispear2RightFourAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH * 1.5f, VIEW_WIDTH * VIEW4_MOVING_RATE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(translationX);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateDispear2LeftFourAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * 1.5f, -VIEW_WIDTH * 0.4f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(translationX);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateRightFour2RightTwoAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH * VIEW4_MOVING_RATE, VIEW_WIDTH*1.02f,VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -DEGREE_SMALL);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateRightTwo2RightFourAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH, VIEW_WIDTH * VIEW4_MOVING_RATE);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_SMALL, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateRightFour2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, VIEW_WIDTH * VIEW4_MOVING_RATE, VIEW_WIDTH * 1.5f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateLeftFour2DispearAnimator(View v) {
        List<Animator> animators = new ArrayList<>();
        v.setPivotX(0);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * VIEW4_MOVING_RATE, -VIEW_WIDTH * 1.5f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateLeftTwo2LeftFourAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, -VIEW_WIDTH * VIEW4_MOVING_RATE);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, DEGREE_SMALL, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, 1, DISPEAR_SCALE_RATE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, 1, DISPEAR_SCALE_RATE);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateLeftFour2LeftTwoAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH * VIEW4_MOVING_RATE, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, DEGREE_SMALL);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, View.SCALE_X, DISPEAR_SCALE_RATE, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, View.SCALE_Y, DISPEAR_SCALE_RATE, 1);
        animators.add(translationX);
        animators.add(rotationY);
        animators.add(scaleX);
        animators.add(scaleY);
        return animators;
    }

    private Collection<Animator> generateCenter2LeftThreeAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, 0, -VIEW_WIDTH);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, 0, -5, -15, -25, -DEGREE_BIG);
        animators.add(translationX);
        animators.add(rotationY);

        return animators;
    }

    private Collection<Animator> generateLeftThree2CenterAnimator(View v) {
        v.setPivotX(VIEW_WIDTH);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(v, View.TRANSLATION_X, -VIEW_WIDTH, 0);
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(v, View.ROTATION_Y, -DEGREE_BIG, -25, -15, -5, 0);
        animators.add(translationX);
        animators.add(rotationY);
        return animators;
    }


    private void enableGroup1() {
        next1.setFocusable(true);
        next1.setClickable(true);
    }

    private void enableGroup2() {
        back2.setClickable(true);
        back2.setFocusable(true);
        next2.setClickable(true);
        next2.setFocusable(true);
    }

    private void enableGroup3() {
        back3.setClickable(true);
        back3.setFocusable(true);
        next3.setClickable(true);
        next3.setFocusable(true);
    }

    private void enableGroup4() {
        back4.setClickable(true);
        back4.setFocusable(true);
        next4.setClickable(true);
        next4.setFocusable(true);
    }

    private void enableGroup5() {
        back5.setClickable(true);
        back5.setFocusable(true);
        next5.setClickable(true);
        next5.setFocusable(true);
    }

    private void enableGroup6() {
        back6.setClickable(true);
        back6.setFocusable(true);
    }

    private void disableGroup1() {
        next1.setClickable(false);
        next1.setFocusable(false);
    }

    private void disableGroup2() {
        next2.setClickable(false);
        next2.setFocusable(false);
        back2.setClickable(false);
        back2.setFocusable(false);
    }

    private void disableGroup3() {
        next3.setClickable(false);
        next3.setFocusable(false);
        back3.setClickable(false);
        back3.setFocusable(false);
    }

    private void disableGroup4() {
        next4.setClickable(false);
        next4.setFocusable(false);
        back4.setClickable(false);
        back4.setFocusable(false);
    }

    private void disableGroup5() {
        next5.setClickable(false);
        next5.setFocusable(false);
        back5.setClickable(false);
        back5.setFocusable(false);
    }

    private void disableGroup6() {
        back6.setClickable(false);
        back6.setFocusable(false);
    }

    private void chooseGroup1() {
        enableGroup1();
        disableGroup2();
        disableGroup3();
        disableGroup4();
        disableGroup5();
        disableGroup6();
    }

    private void chooseGroup2() {
        disableGroup1();
        enableGroup2();
        disableGroup3();
        disableGroup4();
        disableGroup5();
        disableGroup6();
    }

    private void chooseGroup3() {
        disableGroup1();
        disableGroup2();
        enableGroup3();
        disableGroup4();
        disableGroup5();
        disableGroup6();
    }

    private void chooseGroup4() {
        disableGroup1();
        disableGroup2();
        disableGroup3();
        enableGroup4();
        disableGroup5();
        disableGroup6();
    }

    private void chooseGroup5() {
        disableGroup1();
        disableGroup2();
        disableGroup3();
        disableGroup4();
        enableGroup5();
        disableGroup6();
    }

    private void chooseGroup6() {
        disableGroup1();
        disableGroup2();
        disableGroup3();
        disableGroup4();
        disableGroup5();
        enableGroup6();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backbtn2:
                back2Step1();
                break;
            case R.id.backbtn3:
                back2Step2();
                break;
            case R.id.backbtn4:
                back2Step3();
                break;
            case R.id.backbtn5:
                back2Step4();
                break;
            case R.id.backbtn6:
                back2Step5();
                break;
            case R.id.nextbtn1:
                next2Step2();
                break;
            case R.id.nextbtn2:
                next2Step3();
                break;
            case R.id.nextbtn3:
                next2Step4();
                break;
            case R.id.nextbtn4:
                next2Step5();
                break;
            case R.id.nextbtn5:
                next2Step6();
                break;
            default:
                break;
        }
    }

}

package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.AnimationUtil;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/13.
 */
public class AnimationUtilTestActivity extends BaseActivity implements View.OnClickListener{
    Button mBtn;
    Button mRotateBtn;
    ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_util);
        mBtn = (Button) findViewById(R.id.btn);
        mRotateBtn = (Button) findViewById(R.id.rotatebtn);
        mRotateBtn.setOnClickListener(this);
        mImg = (ImageView) findViewById(R.id.img);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AnimationUtil.scaleView(mImg,0.2f);
                AnimationUtil.playJumpAnimation(mImg, 100f);
            }
        });
    }

    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.rotatebtn:
//               AnimationUtil.rotateView(mImg);
               AnimationUtil.playLandAnimation(mImg,100f);
               break;

       }
    }
}

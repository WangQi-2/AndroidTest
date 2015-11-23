package com.wq.androidtest.activity.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/23.
 */
public class ActivityOptionActivity extends BaseActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityoption);
        setUpViews();
    }

    private void setUpViews() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);


        sharedimage = (ImageView) findViewById(R.id.sharedimage);
        sharedimage.setOnClickListener(this);
    }

    ImageView sharedimage;

    @Override
    public void onClick(View v) {
        ActivityOptionsCompat optionsCompat = null;
        switch (v.getId()) {
            case R.id.btn1:
                // TODO: 15/11/23
//                optionsCompat = ActivityOptionsCompat.makeCustomAnimation(mCtx, R.animator.fade_in, R.animator.fade_out);
                break;
            case R.id.btn2:
                // TODO: 15/11/23 how to  set duration
                // TODO: 15/11/23 where is exit animation?
                optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, 0, 0);
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            case R.id.sharedimage:
                // TODO: 15/11/23 一个activity的view和另一个activity的view相关联的动画怎么做？？？
                optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityOptionActivity.this, sharedimage,"sharedimage");
                break;
            default:
                break;
        }
        if (optionsCompat != null) {
            Bundle bundle = optionsCompat.toBundle();
            Intent intent = new Intent(mCtx, ActivityOption2Activity.class);
            startActivity(intent, bundle);
        }
    }
}

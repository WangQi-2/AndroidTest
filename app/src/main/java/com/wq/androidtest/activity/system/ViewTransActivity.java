package com.wq.androidtest.activity.system;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.AnimationUtil;
import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/9/16.
 */
public class ViewTransActivity extends BaseActivity implements View.OnClickListener {

    Button scrollbyBtn;
    Button scrollToBtn;
    Button animBtn;
    Button layoutBtn;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trans);
        img = (ImageView) findViewById(R.id.img);
        scrollbyBtn = (Button) findViewById(R.id.btn1);
        scrollToBtn = (Button) findViewById(R.id.btn2);
        animBtn = (Button) findViewById(R.id.btn3);
        layoutBtn = (Button) findViewById(R.id.btn4);
        scrollbyBtn.setOnClickListener(this);
        scrollToBtn.setOnClickListener(this);
        animBtn.setOnClickListener(this);
        layoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                img.scrollBy(10, 10);
                ToastUtil.showToast(this,"scrollby");
                break;
            case R.id.btn2:
                img.scrollTo(100, 200);
                ToastUtil.showToast(this,"scrollTo");
                break;
            case R.id.btn3:
                AnimationUtil.scaleView(img, 0.5f);
                ToastUtil.showToast(this,"anim");
                break;
            case R.id.btn4:
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) img.getLayoutParams();
                layoutParams.leftMargin += 100;
                layoutParams.topMargin += 100;
                img.setLayoutParams(layoutParams);
                ToastUtil.showToast(this,"layout");
                break;

        }
    }
}

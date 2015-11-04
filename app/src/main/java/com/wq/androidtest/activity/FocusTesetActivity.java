package com.wq.androidtest.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;

/**
 * Created by qiwang on 2015/8/29.
 * view可以取得它上下左右的focusedview，也可以重新设置
 */
public class FocusTesetActivity extends BaseActivity implements View.OnClickListener{

    Button btn;
    Button button;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_test);

        btn = (Button) findViewById(R.id.button0);
        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        //把view的next设置为自身,参数需要指定为它自己，指定为-1是没用的
//        button8.setNextFocusForwardId(R.id.button8);
        button8.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        ToastUtil.showToast(this,"clicked");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

package com.wq.androidtest.activity.system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wq.androidlibrary.util.ToastUtil;
import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/11/30.
 */
public class IntentActivity extends BaseActivity implements View.OnClickListener {

    Button chooseBtn;
    Button resolveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        setUpViews();
    }

    private void setUpViews() {
        chooseBtn = (Button) findViewById(R.id.choose_btn);
        chooseBtn.setOnClickListener(this);
        resolveBtn = (Button) findViewById(R.id.resolve_btn);
        resolveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.choose_btn:

                intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent = Intent.createChooser(intent, "test");
                startActivity(intent);

                break;
            case R.id.resolve_btn:

                intent = new Intent();
                intent.setAction("com.wq.action.test");

                if (intent.resolveActivity(getPackageManager()) == null) {
                    ToastUtil.showToast(mCtx, "intent can not resolve");
                } else {
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }
}

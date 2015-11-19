package com.wq.androidtest.activity.customview;

import android.os.Bundle;
import android.view.View;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.BaseActivity;
import com.wq.androidtest.util.ViewFinder;
import com.wq.androidtest.view.customview.IndicatorView;

/**
 * Created by wangqi on 15/11/19.
 */
public class IndicatorActivity extends BaseActivity {

    IndicatorView indicator;

    // TODO: 15/11/19 add pagerview
    // TODO: 15/11/19 ui : circle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        indicator = ViewFinder.find(decorView, R.id.indicator);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.next:
                indicator.moveToNext();
                break;
            case R.id.pre:
                indicator.moveToPrevious();
                break;
        }
    }
}

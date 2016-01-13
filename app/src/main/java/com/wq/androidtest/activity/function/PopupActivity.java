package com.wq.androidtest.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.base.BaseActivity;

/**
 * Created by wangqi on 15/10/21.
 */
public class PopupActivity extends BaseActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        button = (Button) findViewById(R.id.button);
    }

    public void click(View view) {
        showpopup();
    }

    private void showpopup() {
        Button button = new Button(this);
        button.setText("dismiss");
        final PopupWindow popupWindow = new PopupWindow(button, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg1));
        popupWindow.showAsDropDown(PopupActivity.this.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}

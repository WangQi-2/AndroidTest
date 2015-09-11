package com.wq.androidtest.view.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by qiwang on 2015/8/29.
 * 用来更改button的focuschang行为
 */
public class ChangeFocusButton extends Button {
    public ChangeFocusButton(Context context) {
        super(context);
        init();
    }

    public ChangeFocusButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChangeFocusButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    requestFocus();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //FIXME
//        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//            //这里拿到的id是-1，干
//            int downId = getNextFocusDownId();
//            Context context = getContext();
//            if (context instanceof Activity) {
//                View downView = ((Activity) context).findViewById(downId);
//                if (downView != null && this.getBottom() <= downView.getTop()) {
//                    setNextFocusDownId(this.getId());
//                }
//            }
//        }

        return super.onKeyDown(keyCode, event);
    }


}

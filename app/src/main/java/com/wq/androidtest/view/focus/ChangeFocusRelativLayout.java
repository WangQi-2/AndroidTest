package com.wq.androidtest.view.focus;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by qiwang on 2015/8/29.
 * 用来改变容器findfocus的返回
 */
public class ChangeFocusRelativLayout extends RelativeLayout {
    public ChangeFocusRelativLayout(Context context) {
        super(context);
        init();
    }

    public ChangeFocusRelativLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChangeFocusRelativLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public View findFocus() {
        View focusView = super.findFocus();
        View curFocusView = getFocusedChild();

        if (curFocusView.getBottom() <= focusView.getTop()) {
            return curFocusView;
        } else {
            return focusView;
        }
    }
}

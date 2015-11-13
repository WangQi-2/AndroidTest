package com.wq.androidtest.util;

import android.content.res.Resources;

import com.wq.androidtest.R;
import com.wq.androidtest.base.TestApplication;

/**
 * Created by wangqi on 15/11/13.
 */
public class ResUtil {

    static Resources mRes;

    static {
        mRes = TestApplication.getsInstance().getResources();
    }

    public static int[] getColors() {
        return mRes.getIntArray(R.array.colors);
    }
}

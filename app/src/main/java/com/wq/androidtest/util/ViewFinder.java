package com.wq.androidtest.util;

import android.view.View;

/**
 * Created by wangqi on 15/11/13.
 */
public class ViewFinder {

    public static <T extends View> T find(View view, int id) {
        return (T) view.findViewById(id);
    }
}

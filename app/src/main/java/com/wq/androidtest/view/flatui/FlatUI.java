package com.wq.androidtest.view.flatui;

import com.wq.androidtest.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/9/30.
 * fixme 目前theme就是一个背景色而已
 */
public class FlatUI {
    private static List<FlatView> flatViews = new ArrayList<>();

    public static void addFlatView(FlatView flatView) {
        if (flatViews.contains(flatView)) {
            return;
        }
        flatViews.add(flatView);
    }

    public static void changeTheme(int color) {
        Logger.e("changeTheme:" + color);
        for (FlatView flatView : flatViews) {
            flatView.getAttributes().setColor(color);
        }

    }
}

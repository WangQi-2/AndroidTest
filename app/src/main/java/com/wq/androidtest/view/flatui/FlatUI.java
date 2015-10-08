package com.wq.androidtest.view.flatui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqi on 15/9/30.
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
        for (FlatView flatView : flatViews) {
            flatView.getAttributes().setColor(color);
        }

    }
}

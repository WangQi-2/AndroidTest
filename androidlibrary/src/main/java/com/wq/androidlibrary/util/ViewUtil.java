package com.wq.androidlibrary.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by wangqi on 15/9/10.
 */
public class ViewUtil {
    public static void setListViewHeight(ListView listView, int height) {

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height;
        listView.setLayoutParams(params);
    }

    public static void setListViewHeight(ListView listView) {
        int height = 0;
        ListAdapter adapter = listView.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, listView);
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += listView.getDividerHeight() * (adapter.getCount() - 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height;
        listView.setLayoutParams(params);
    }
}

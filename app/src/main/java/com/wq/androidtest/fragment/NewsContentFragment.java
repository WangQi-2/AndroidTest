package com.wq.androidtest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wq.androidtest.model.News;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by wangqi on 15/9/8.
 */
public class NewsContentFragment extends Fragment {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        textView = new TextView(getActivity());
        textView.setTextSize(30);
        return textView;
    }

    @Subscribe
    public void onEventMainThread(News news) {
        if (news != null) {
            textView.setText(news.getContent());
        }
    }
}

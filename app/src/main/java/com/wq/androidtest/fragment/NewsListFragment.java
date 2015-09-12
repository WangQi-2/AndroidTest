package com.wq.androidtest.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wq.androidtest.model.News;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by wangqi on 15/9/8.
 */
public class NewsListFragment extends ListFragment {

    ArrayList<News> newses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newses = new ArrayList<News>();
        newses.add(new News("news1", "news1"));
        newses.add(new News("news2", "news2"));
        newses.add(new News("news3", "news3"));
        newses.add(new News("news4", "news4"));
        newses.add(new News("news5", "news5"));
        newses.add(new News("news6", "news6"));

        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(2 * 1000);

                EventBus.getDefault().post(newses);
            }
        }.start();
    }

    @Subscribe
    public void onEventMainThread(List<News> newslist) {
        List<String> titles;
        titles = new ArrayList<>();
        for (News news : newslist) {
            titles.add(news.getTitle());
        }
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, android.R.id.text1, titles));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        EventBus.getDefault().post(getListView().getItemAtPosition(position));
    }
}

package com.wq.androidtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.activity.DemoTableActivity;
import com.wq.androidtest.model.DemoEntryModel;

import java.util.List;

/**
 * Created by wangqi on 15/9/14.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    public static List<DemoEntryModel> models
            = DemoTableActivity.demoEntryModels;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text_1, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        viewHolder.textView.setText(models.get(i % models.size()).getDes());
    }

    @Override
    public int getItemCount() {
        return models.size() * 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text1);
        }
    }
}

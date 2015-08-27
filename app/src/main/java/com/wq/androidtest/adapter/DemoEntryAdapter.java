package com.wq.androidtest.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.wq.androidtest.model.DemoEntryModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by qiwang on 2015/8/27.
 */
public final class DemoEntryAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<DemoEntryModel> demoEntryModels;

    public DemoEntryAdapter(@NonNull Context context, @NonNull ArrayList<DemoEntryModel> demos) {
        demoEntryModels = demos;
        mContext = context;
    }

    @Override
    public int getCount() {
        return demoEntryModels.size();
    }

    @Override
    public Object getItem(int position) {
        return demoEntryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = new TextView(mContext);
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(demoEntryModels.get(position).getDes());
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }

    public ArrayList<DemoEntryModel> getDemoEntryModels() {
        return demoEntryModels;
    }

    public void setDemoEntryModels(@NonNull ArrayList<DemoEntryModel> demoEntryModels) {
        this.demoEntryModels = demoEntryModels;
    }
}

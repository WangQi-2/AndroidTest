package com.wq.androidtest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidtest.model.DemoEntryModel;

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
        TextView holder;
        if (convertView == null) {
            TextView textView = new TextView(mContext);
            textView.setTextAppearance(mContext, R.style.AppTheme_WWTextView);
            //TODO 这里true会影响item的点击事件,结合tv开发我或许可以知道为什么
//            textView.setFocusable(true);
            textView.setTextSize(20);
//            textView.setClickable(true);
            textView.setGravity(Gravity.CENTER);
            textView.setTag(textView);
            textView.setTextColor(Color.RED);
//            textView.setBackgroundResource(R.drawable.seletor_btn_bg);
            //在setbgres之后调用
            textView.setPadding(0, 10, 0, 10);
            textView.setTag(R.id.position,new Integer(position));
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ToastUtil.showToast(mContext,"textview clicked");
//                    int position = (Integer) v.getTag(R.id.position);
//                    Intent i = null;
//                    if (demoEntryModels.get(position).getChilds() != null) {
//                        i = new Intent(mContext, DemoTableActivity.class);
//                        i.putExtra(BaseActivity.FUNC_MODELS, (Serializable) demoEntryModels.get(position).getChilds());
//                    } else {
//                        i = new Intent(mContext, demoEntryModels.get(position).getClazz());
//                    }
//                    i.putExtra(BaseActivity.TITLE, demoEntryModels.get(position).getDes());
//                    mContext.startActivity(i);
//                }
//            });
            holder = textView;
        } else {
            holder = (TextView) convertView.getTag();
        }
        holder.setText(demoEntryModels.get(position).getDes());
        return holder;
    }

    public ArrayList<DemoEntryModel> getDemoEntryModels() {
        return demoEntryModels;
    }

    public void setDemoEntryModels(@NonNull ArrayList<DemoEntryModel> demoEntryModels) {
        this.demoEntryModels = demoEntryModels;
    }
}

package com.itheima.googleplay.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * 针对BaseAdapter简单封装,针对的是其中的3个方法(getCount,getItem,getItemId)
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public List<T> mDataSets;

    public MyBaseAdapter(List<T> dataSets) {
        mDataSets = dataSets;
    }

    @Override
    public int getCount() {
        if (mDataSets != null) {
            return mDataSets.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDataSets != null) {
            return mDataSets.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

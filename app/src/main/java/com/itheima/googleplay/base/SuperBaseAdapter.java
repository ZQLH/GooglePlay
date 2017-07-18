package com.itheima.googleplay.base;

import android.view.View;
import android.view.ViewGroup;

import com.itheima.googleplay.holder.BaseHolder;

import java.util.List;

/**
 * 针对MyBaseAdapter里面的getView方法，在getView方法中引入了BaseHolder这个类
 */
public abstract class SuperBaseAdapter<T> extends MyBaseAdapter {
    public SuperBaseAdapter(List<T> dataSets) {
        super(dataSets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         /*--------------- 决定根布局(itemView) ---------------*/
        BaseHolder holder = null;
        if (convertView == null) {
            //创建holer对象
            holder = getSpecialBaseHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
            /*--------------- 得到数据,然后绑定数据 ---------------*/
        //data
        Object  data =  mDataSets.get(position);
        holder.setDataAndRefreshHolderView(data);
        return holder.mHolderView;//其实这个convertView是经过了数据绑定的convertView
    }

    /**
     * @return
     * @des 得到BaseHolder具体的子类对象
     * @des 在SuperBaseAdapter中不知道如何创建BaseHolder的子类对象, 所以只能交给子类, 子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     */
    public abstract BaseHolder getSpecialBaseHolder();
}

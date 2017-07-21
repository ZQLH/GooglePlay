package com.itheima.googleplay.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.itheima.googleplay.holder.BaseHolder;

import java.util.List;

/**
 * 针对MyBaseAdapter里面的getView方法，在getView方法中引入了BaseHolder这个类
 */
public abstract class SuperBaseAdapter<T> extends MyBaseAdapter {
    /**  加载更多*/
    public static  final int VIEWTYPE_LOADMORE=0;
    /**  普通的Item*/
    public static final int VIEWTYPE_NORMAL=1;
    public SuperBaseAdapter(List<T> dataSets) {
        super(dataSets);
    }

    @Override
    public int getCount() {
        return super.getCount()+1;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;//1(普通类型)+1(加载更多) = 2
    }

    @Override
    public int getItemViewType(int position) {
        //如果是最后一个条目就返回另外一种
        if (position==getCount()-1){
            return VIEWTYPE_LOADMORE;
        }else{
            return  VIEWTYPE_NORMAL;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         /*--------------- 决定根布局(itemView) ---------------*/
        BaseHolder holder = null;
        if (convertView == null) {
            holder = getSpecialBaseHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        Object  data =  mDataSets.get(position);
        //绑定了一个数据，就刷新一下该数据
        holder.setDataAndRefreshHolderView(data);
        return holder.mHolderView;//其实这个convertView是经过了数据绑定的convertView
    }

    /**
     * @return
     * @des 得到BaseHolder具体的子类对象,比如HomeAdapter
     * @des 在SuperBaseAdapter中不知道如何创建BaseHolder的子类对象, 所以只能交给子类, 子类必须实现
     * @des 必须实现, 但是不知道具体实现, 定义成为抽象方法, 交给子类具体实现
     */
    public abstract BaseHolder getSpecialBaseHolder();
}

package com.itheima.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.utils.UIUtils;

import java.util.Random;

/**
 * 创建者     伍碧林
 * 版权       传智播客.黑马程序员
 * 描述	      ${TODO}
 */
public class HotFragment extends BaseFragment {
    /**
     * @des 在子线程中真正的加载具体的数据
     * @called triggerLoadData()方法被调用的时候
     */
    @Override
    public LoadingPager.LoadedResult initData() {
        SystemClock.sleep(2000);//模拟耗时的网络请求

        Random random = new Random();
        int index = random.nextInt(3);// 0  1 2

        LoadingPager.LoadedResult[] loadedResults = {LoadingPager.LoadedResult.SUCCESS, LoadingPager.LoadedResult.EMPTY, LoadingPager.LoadedResult.ERROR};
        //随机返回一种情况
        return loadedResults[index];//数据加载完成之后的状态(成功,失败,空)
    }

    /**
     * @return
     * @des 决定成功视图长什么样子(需要定义成功视图)
     * @des 数据和视图的绑定过程
     * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
     */
    @Override
    public View initSuccessView() {
        //view
        TextView successView = new TextView(UIUtils.getContext());
        successView.setGravity(Gravity.CENTER);
        successView.setTextColor(Color.RED);
        //data
        String data = this.getClass().getSimpleName();
        //data+view
        successView.setText(data);

        return successView;
    }

}

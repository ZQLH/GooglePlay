package com.itheima.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.itheima.googleplay.base.BaseFragment;
import com.itheima.googleplay.holder.BaseHolder;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.adapter.SuperBaseAdapter;
import com.itheima.googleplay.bean.HomeBean;
import com.itheima.googleplay.bean.ItemBean;
import com.itheima.googleplay.holder.HomeHolder;
import com.itheima.googleplay.protocol.HomeProtocol;
import com.itheima.googleplay.utils.UIUtils;

import java.util.List;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    private List<String> mDatas;
    private List<ItemBean> mItemBeans;
    private List<String> mPictures;

    /**
     * @des 在子线程中真正的加载具体的数据
     * @called triggerLoadData()方法被调用的时候
     */
    @Override
    public LoadingPager.LoadedResult initData() {
         /*--------------- 协议进行简单封装以后 ---------------*/
        HomeProtocol protocol = new HomeProtocol();
        try {
            HomeBean homeBean = protocol.loadData(0);
            LoadingPager.LoadedResult state = checkResult(homeBean);
            if (state != LoadingPager.LoadedResult.SUCCESS) {//说明homeBean有问题,homeBean==null
                return state;
            }
            state = checkResult(homeBean.list);
            if (state != LoadingPager.LoadedResult.SUCCESS) {//说明list有问题,list.size==0
                return state;
            }
            //走到这里来说明是成功的
            //保存数据到成员变量
            mItemBeans = homeBean.list;
            mPictures = homeBean.picture;
            //返回相应的状态
            return state;//successs的状态
        } catch (Exception e){
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    /**
     * @return 加载成功的视图
     * @des 决定成功视图长什么样子(需要定义成功视图)
     * @des 数据和视图的绑定过程
     * @called triggerLoadData()方法被调用, 而且数据加载完成了, 而且数据加载成功
     */
    @Override
    public View initSuccessView() {
        //view
        ListView listView = new ListView(UIUtils.getContext());
        //data-->dataSets-->在成员变量里面
        //data+view
        listView.setAdapter(new HomeAdapter(mItemBeans));
        return listView;
    }

    class HomeAdapter extends SuperBaseAdapter<ItemBean> {
        public HomeAdapter(List<ItemBean> dataSets) {
            super(dataSets);
        }

        /**
         * @return
         * @des 得到BaseHolder具体的子类对象
         */
        @Override
        public BaseHolder getSpecialBaseHolder() {
            return new HomeHolder();
        }
    }
}

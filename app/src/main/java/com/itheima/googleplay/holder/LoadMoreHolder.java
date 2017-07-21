package com.itheima.googleplay.holder;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.InjectView;

/**
 * Created by lala on 2017/7/21.
 */

public class LoadMoreHolder extends BaseHolder {
    public static final int LOADMORE_LOADING = 0;
    public static final int LOADMORE_ERROR = 1;
    public static final int LOADMORE_NONE = 2;
    @InjectView(R.id.item_loadmore_container_loading)
    LinearLayout itemLoadmoreContainerLoading;
    @InjectView(R.id.item_loadmore_tv_retry)
    TextView itemLoadmoreTvRetry;
    @InjectView(R.id.item_loadmore_container_retry)
    LinearLayout itemLoadmoreContainerRetry;

    @Override
    public View initHolderView() {
        View holderView = View.inflate(UIUtils.getContext(), R.layout.item_loadmore, null);
        return holderView;
    }

    /**
     * 刷新UI
     * 传递进来的数据类型有什么用?-->决定ui的具体展现
     */
    @Override
    public void refreshHolderView(Object data) {
        itemLoadmoreContainerLoading.setVisibility(View.GONE);
        itemLoadmoreContainerRetry.setVisibility(View.GONE);
    }
}

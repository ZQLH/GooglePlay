package com.itheima.googleplay.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.ItemBean;
import com.itheima.googleplay.conf.Constants;
import com.itheima.googleplay.utils.UIUtils;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lala on 2017/7/2.
 */

public class DetailSafeHolder extends BaseHolder<ItemBean> implements View.OnClickListener {
    @InjectView(R.id.app_detail_safe_iv_arrow)
    ImageView appDetailSafeIvArrow;
    @InjectView(R.id.app_detail_safe_pic_container)
    LinearLayout appDetailSafePicContainer;
    @InjectView(R.id.app_detail_safe_des_container)
    LinearLayout appDetailSafeDesContainer;

    /** 标识当前状态是打开还是关闭 */
    private boolean isOpen = true;

    @Override
    public View initHolderView() {
        View holderView = View.inflate(UIUtils.getContext(), R.layout.item_detail_safe, null);
        ButterKnife.inject(this, holderView);
        holderView.setOnClickListener(this);
        return holderView;
    }

    @Override
    public void refreshHolderView(ItemBean data) {
        List<ItemBean.ItemSafeBean> safe = data.safe;
        for (int i=0;i<safe.size();i++){
            ItemBean.ItemSafeBean itemSafeBean=safe.get(i);
            String safeDes=itemSafeBean.safeDes;
            int safeDesColor=itemSafeBean.safeDesColor;
            String safeDesUrl=itemSafeBean.safeDesUrl;
            String safeUrl=itemSafeBean.safeUrl;
  /*---------------  往mAppDetailSafePicContainer 容器动态加载孩子 ---------------*/
            ImageView ivIcon=new ImageView(UIUtils.getContext());
            Picasso.with(UIUtils.getContext()).load(Constants.URLS.IMGBASEURL + safeUrl).into(ivIcon);
            appDetailSafePicContainer.addView(ivIcon);
  /*---------------  往mAppDetailSafeDesContainer 容器动态加载孩子 ---------------*/
            LinearLayout line=new LinearLayout(UIUtils.getContext());
            // 构建描述文本
            TextView tvDesNote = new TextView(UIUtils.getContext());
            tvDesNote.setSingleLine(true);
            tvDesNote.setText(safeDes);
            if (safeDesColor==0){
                tvDesNote.setTextColor(UIUtils.getColor(R.color.app_detail_safe_normal));
            } else {
                tvDesNote.setTextColor(UIUtils.getColor(R.color.app_detail_safe_warning));
            }
            // 构建描述图标
            ImageView ivDesIcon = new ImageView(UIUtils.getContext());
            Picasso.with(UIUtils.getContext()).load(Constants.URLS.IMGBASEURL + safeDesUrl).into(ivDesIcon);
            line.addView(ivDesIcon);
            line.addView(tvDesNote);
            appDetailSafeDesContainer.addView(line);

            changeSafeDesContainerHeight(false);
        }
    }


    @Override
    public void onClick(View v) {
        changeSafeDesContainerHeight(true);
    }

    private void changeSafeDesContainerHeight(boolean isAnimation) {
        if (isOpen){
            int start=appDetailSafeDesContainer.getMeasuredHeight();
            int end=0;
            if (isAnimation){
                doAnimation(start,end);
            }else{
                ViewGroup.LayoutParams layoutParams=appDetailSafeDesContainer.getLayoutParams();
                layoutParams.height=end;
                //重新设置layoutParams
                appDetailSafeDesContainer.setLayoutParams(layoutParams);
            }
        }else{
            //展开 mAppDetailSafeDesContainer 高度  0-->应有的高度
            int end=0;
            appDetailSafeDesContainer.measure(0,0);
            int start=0;
            if (isAnimation){
                doAnimation(start,end);
            }else {
                ViewGroup.LayoutParams layoutParams=appDetailSafeDesContainer.getLayoutParams();
                layoutParams.height=end;
                appDetailSafeDesContainer.setLayoutParams(layoutParams);
            }
        }
        isOpen=!isOpen;
    }

    private void doAnimation(int start, int end) {
        ValueAnimator animator=ValueAnimator.ofInt(start,end);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int tempHeight= (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams=appDetailSafeDesContainer.getLayoutParams();
                layoutParams.height=tempHeight;
                appDetailSafeDesContainer.setLayoutParams(layoutParams);
            }
        });
        if (isOpen){
            ObjectAnimator.ofFloat(appDetailSafeDesContainer,"rotation",180,0).start();
        }else {
            ObjectAnimator.ofFloat(appDetailSafeDesContainer,"rotation",0,180).start();

        }
    }
}

package com.itheima.googleplay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.itheima.googleplay.R;
import com.itheima.googleplay.base.LoadingPager;
import com.itheima.googleplay.bean.ItemBean;
import com.itheima.googleplay.holder.DetailDesHolder;
import com.itheima.googleplay.holder.DetailDownloadHolder;
import com.itheima.googleplay.holder.DetailInfoHolder;
import com.itheima.googleplay.holder.DetailPicHolder;
import com.itheima.googleplay.holder.DetailSafeHolder;
import com.itheima.googleplay.protocol.DetailProtocol;
import com.itheima.googleplay.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends AppCompatActivity {
    @InjectView(R.id.detail_fl_download)
    FrameLayout detailDownload;
    @InjectView(R.id.detail_fl_info)
    FrameLayout detailInfo;
    @InjectView(R.id.detail_fl_safe)
    FrameLayout detailSafe;
    @InjectView(R.id.detail_fl_pic)
    FrameLayout detailPic;
    @InjectView(R.id.detail_fl_des)
    FrameLayout detailDes;
    private String mPackageName;
    private LoadingPager mLoadingPager;
    private ItemBean mItemBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingPager = new LoadingPager(this) {
            @Override
            public LoadedResult initData() {
                return DetailActivity.this.initData();
            }

            @Override
            public View initSuccessView() {
                return DetailActivity.this.initSuccessView();
            }
        };
        setContentView(mLoadingPager);
        ButterKnife.inject(this);
        init();
        triggerLoadData();
    }

    private void triggerLoadData() {
        mLoadingPager.triggerLoadData();
    }

    private LoadingPager.LoadedResult initData() {
        DetailProtocol protocol = new DetailProtocol(mPackageName);
        try {
            protocol.loadData(0);
            if (mItemBean != null) {
                return LoadingPager.LoadedResult.SUCCESS;
            } else {
                return LoadingPager.LoadedResult.EMPTY;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResult.ERROR;
        }
    }

    private void init() {
        mPackageName = getIntent().getStringExtra("packageName");
        Toast.makeText(getApplicationContext(), mPackageName, Toast.LENGTH_SHORT).show();
    }

    private View initSuccessView() {
        //successView是一个大的控件，里面被分成5个小的控件
        View successView = View.inflate(UIUtils.getContext(), R.layout.layout_detail, null);
        ButterKnife.inject(this,successView);
        //        往应用的信息部分这个容器里面添加内容
        DetailInfoHolder detailInfoHolder=new DetailInfoHolder();
        detailInfo.addView(detailInfoHolder.mHolderView);
        detailInfoHolder.setDataAndRefreshHolderView(mItemBean);
//        往应用的安全部分这个容器里面添加内容
        DetailSafeHolder detailSafeHolder=new DetailSafeHolder();
        detailSafe.addView(detailSafeHolder.mHolderView);
        detailSafeHolder.setDataAndRefreshHolderView(mItemBean);

        //        往应用的截图部分这个容器里面添加内容
        DetailPicHolder detailPicHolder = new DetailPicHolder();
        detailPic.addView(detailPicHolder.mHolderView);
        detailPicHolder.setDataAndRefreshHolderView(mItemBean);

//        往应用的描述部分这个容器里面添加内容
        DetailDesHolder detailDesHolder = new DetailDesHolder();
        detailDes.addView(detailDesHolder.mHolderView);
        detailDesHolder.setDataAndRefreshHolderView(mItemBean);

//        往应用的下载部分这个容器里面添加内容
        DetailDownloadHolder detailDownloadHolder = new DetailDownloadHolder();
        detailDownload.addView(detailDownloadHolder.mHolderView);
        detailDownloadHolder.setDataAndRefreshHolderView(mItemBean);


        return successView;
    }
}

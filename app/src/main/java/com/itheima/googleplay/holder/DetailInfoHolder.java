package com.itheima.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.googleplay.R;
import com.itheima.googleplay.bean.ItemBean;
import com.itheima.googleplay.conf.Constants;
import com.itheima.googleplay.utils.StringUtils;
import com.itheima.googleplay.utils.UIUtils;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lala on 2017/7/2.
 */

public class DetailInfoHolder extends BaseHolder<ItemBean> {
    @InjectView(R.id.app_detail_info_iv_icon)
    ImageView appDetailInfoIvIcon;
    @InjectView(R.id.app_detail_info_tv_name)
    TextView appDetailInfoTvName;
    @InjectView(R.id.app_detail_info_rb_star)
    RatingBar appDetailInfoRbStar;
    @InjectView(R.id.app_detail_info_tv_downloadnum)
    TextView appDetailInfoTvDownloadnum;
    @InjectView(R.id.app_detail_info_tv_version)
    TextView appDetailInfoTvVersion;
    @InjectView(R.id.app_detail_info_tv_time)
    TextView appDetailInfoTvTime;
    @InjectView(R.id.app_detail_info_tv_size)
    TextView appDetailInfoTvSize;

    @Override
    public View initHolderView() {
        View holderView = View.inflate(UIUtils.getContext(), R.layout.item_detail_info, null);
        ButterKnife.inject(this, holderView);
        return holderView;
    }

    @Override
    public void refreshHolderView(ItemBean data) {
        String date=UIUtils.getResources().getString(R.string.detail_date,data.date);
        String downLoadNum=UIUtils.getResources().getString(R.string.detail_downloadnum,data.downloadNum);
        String size=UIUtils.getResources().getString(R.string.detail_size, StringUtils.formatFileSize(data.size));
        String version = UIUtils.getResources().getString(R.string.detail_version, data.version);

        appDetailInfoTvName.setText(data.name);
        appDetailInfoTvVersion.setText(version);
        appDetailInfoTvTime.setText(date);
        appDetailInfoTvSize.setText(size);
        appDetailInfoTvDownloadnum.setText(downLoadNum);

        Picasso.with(UIUtils.getContext()).load(Constants.URLS.IMGBASEURL+data.iconUrl).into(appDetailInfoIvIcon);

    }

}

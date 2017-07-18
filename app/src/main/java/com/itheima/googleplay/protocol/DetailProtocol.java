package com.itheima.googleplay.protocol;

import android.content.ClipData;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.itheima.googleplay.bean.ItemBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lala on 2017/7/2.
 */

public class DetailProtocol extends BaseProtocol{
    public String packageName;

    public DetailProtocol(String packageName) {
        this.packageName = packageName;
    }

    @NonNull
    @Override
    public String getInterfaceKey() {
        return "detail";
    }

    @Override
    protected ItemBean parseJson(String resJsonString) {
        Gson gson=new Gson();
        ItemBean itemBean=gson.fromJson(resJsonString,ItemBean.class);
        return itemBean;
    }

    @NonNull
    @Override
    public Map<String, Object> getParamsMap(int index) {
        Map<String,Object> parasmMap=new HashMap<>();
        parasmMap.put("packageName",packageName);
        return parasmMap;
    }
}

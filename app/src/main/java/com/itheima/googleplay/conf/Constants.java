package com.itheima.googleplay.conf;

import com.itheima.googleplay.utils.LogUtils;

/**
 * 常量类
 */
public class Constants {
    /**
     * LogUtils.LEVEL_ALL:打开日志(显示所有的日志输出)
     * LogUtils.LEVEL_OFF:关闭日志(屏蔽所有的日志输出)
     */
    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;
    public static final long PROTOCOLTIMEOUT = 5 * 60 * 1000;//5分钟

    public static final class URLS {
        public static final String BASEURL = "http://192.168.125.58:8080/GooglePlayServer/";
        public static final String IMGBASEURL = BASEURL + "image?name=";
    }

    public static final class REQ {

    }

    public static final class RES {

    }

    public static final class PAY {
        public static final int PAYTYPE_ZHIFUBAO = 0;
        public static final int PAYTYPE_WEIXIN = 1;
    }

}

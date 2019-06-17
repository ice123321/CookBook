package com.wei.cookbook.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：赵若位
 * 时间：2018/11/29 11:18
 * 邮箱：1070138445@qq.com
 * 功能：
 */
public class NetworkUtils
{

    /*判断当前是否有网络连接*/
    public static boolean isAvailable(Context context)
    {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = manager.getActiveNetworkInfo();
        return (net != null && net.isAvailable());
    }
}

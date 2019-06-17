package com.wei.cookbook.utils;

import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * 作者：赵若位
 * 时间：2018/6/21 10:07
 * 邮箱：1070138445@qq.com
 * 功能：
 */

public class LogUtils
{
    private static boolean isDebug = true;
    private static String TAG = "wei";

    public static void v(String msg)
    {
        if (isDebug)
        {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg)
    {
        if (isDebug)
        {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg)
    {
        if (isDebug)
        {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg)
    {
        if (isDebug)
        {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg)
    {
        if (isDebug)
        {
            Log.e(TAG, msg);
        }
    }


    public static void json(String msg)
    {
        if (isDebug)
        {
            Logger.json(msg);
        }
    }


}

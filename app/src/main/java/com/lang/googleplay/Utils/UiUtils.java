package com.lang.googleplay.Utils;

import android.content.res.Resources;

import com.lang.googleplay.BaseApplication;

/**
 * Created by Lang on 2015/7/23.
 */
public class UiUtils {
    /**
     * 获取到字符数组
     * @param tabNames 字符数组的id
     */
    public static String[] getStringArray(int tabNames){
        return getResources().getStringArray(tabNames);
    }

    private static Resources getResources() {
        return BaseApplication.getApplication().getResources();
    }
}

package com.lang.googleplay;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 代表当前应用程序
 * Created by Lang on 2015/7/23.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;
    private static int mainId;
    private static Handler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainId = android.os.Process.myTid();
        handler = new Handler();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public static int getMainId() {
        return mainId;
    }

    public static Handler getHandler() {
        return handler;
    }

}

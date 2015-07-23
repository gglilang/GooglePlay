package com.lang.googleplay;

import android.app.Application;

/**
 * 代表当前应用程序
 * Created by Lang on 2015/7/23.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static BaseApplication getApplication() {
        return application;
    }
}

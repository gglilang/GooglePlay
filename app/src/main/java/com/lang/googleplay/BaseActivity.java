package com.lang.googleplay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public class BaseActivity extends ActionBarActivity {

    // 管理所有运行的Activity
    public final static List<BaseActivity> mActivities = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }
        init();
        initView();
        initActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }

    public void killAll() {
        // 复制一份mActivities集合，因为集合的迭代会出现问题
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<>(mActivities);
        }
        for (BaseActivity baseActivity : copy) {
            baseActivity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void initActionBar() {

    }

    protected void initView() {

    }

    protected void init() {

    }
}

package com.lang.googleplay.adapter;

import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.holder.BaseHolder;
import com.lang.googleplay.holder.ListBaseHolder;

import java.util.List;

/**
 * Created by Lang on 2015/7/27.
 */
public abstract class ListBaseAdapter extends DefaultAdapter<AppInfo> {
    public ListBaseAdapter(List<AppInfo> datas) {
        super(datas);
    }

    @Override
    public BaseHolder getHolder() {
        return new ListBaseHolder();
    }

    @Override
    protected abstract List<AppInfo> onLoad();

}

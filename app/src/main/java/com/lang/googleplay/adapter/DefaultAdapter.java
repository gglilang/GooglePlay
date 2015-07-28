package com.lang.googleplay.adapter;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lang.googleplay.holder.BaseHolder;
import com.lang.googleplay.holder.MoreHolder;
import com.lang.googleplay.manager.ThreadManager;
import com.lang.googleplay.utils.UiUtils;

import java.util.List;

/**
 * Created by Lang on 2015/7/27.
 */
public abstract class DefaultAdapter<Data> extends BaseAdapter {

    private List<Data> datas;
    private static final int DEFAULT_ITEM = 0;
    private static final int MORE_ITEM = 1;

    public DefaultAdapter(List<Data> datas) {
        this.datas = datas;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 根据位置判断当前条目是什么类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {
            return MORE_ITEM;
        } else {
            return DEFAULT_ITEM;
        }
    }

    /**
     * 当前ListView有几种不同的条目类型
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;

        switch (getItemViewType(position)) {
            case MORE_ITEM:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                    System.out.println("复用复用。。。。");
                }
                break;
            case DEFAULT_ITEM:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                holder.refreshView(getItem(position));
                break;
        }

        return holder.getContentView();
    }

    private MoreHolder holder;

    private BaseHolder getMoreHolder() {
        if (holder != null) {
            return holder;
        } else {
            holder = new MoreHolder(this);
            return holder;
        }
    }

    public abstract BaseHolder getHolder();

    /**
     * 当加载更多条目显示的时候 调用该方法
     */
    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                // 在子线程中加载更多
                final List<Data> newDatas = onLoad();

                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newDatas == null) {
                            // 连接服务器失败
                            holder.setData(MoreHolder.LOAD_ERROR);
                        } else if (newDatas.size() == 0) {
                            // 服务器已经没有额外数据了
                            holder.setData(MoreHolder.HAS_NO_MORE);
                        } else {
                            // 成功了
                            holder.setData(MoreHolder.HAS_MORE);
                            // 给集合追加新的数据
                            datas.addAll(newDatas);
                            notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }

    protected abstract List<Data> onLoad();
}

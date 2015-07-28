package com.lang.googleplay.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.lang.googleplay.R;
import com.lang.googleplay.adapter.DefaultAdapter;
import com.lang.googleplay.utils.UiUtils;

/**
 * Created by Lang on 2015/7/27.
 */
public class MoreHolder extends BaseHolder<Integer> {

    public static final int HAS_NO_MORE = 0;    // 没有额外数据了
    public static final int LOAD_ERROR = 1; // 加载失败
    public static final int HAS_MORE = 2;   // 有额外数据

    private DefaultAdapter adapter;

    private RelativeLayout rl_more_loading;
    private RelativeLayout rl_more_error;

    public MoreHolder(DefaultAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 当Holder显示的时候 显示什么样子
     * @return
     */
    @Override
    public View initView() {
        View view = UiUtils.inflate(R.layout.load_more);
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }

    @Override
    public void refreshView(Integer data) {
        rl_more_error.setVisibility(data == LOAD_ERROR ? View.VISIBLE : View.GONE);
        rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
    }

    @Override
    public View getContentView() {

        loadMore();
        return super.getContentView();
    }

    // 请求服务器，加载下一批数据
    private void loadMore() {
        // 交给Adapter 让Adapter加载更多数据
        adapter.loadMore();
    }
}

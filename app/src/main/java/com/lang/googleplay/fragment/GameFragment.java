package com.lang.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.adapter.ListBaseAdapter;
import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.protocol.GameProtocol;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.view.BaseListView;
import com.lang.googleplay.view.LoadingPage;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public class GameFragment extends BaseFragment {

    private List<AppInfo> datas;


    @Override
    public LoadingPage.LoadResult load() {
        GameProtocol protocol = new GameProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }

    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas) {
            @Override
            protected List<AppInfo> onLoad() {
                GameProtocol protocol = new GameProtocol();
                List<AppInfo> load = protocol.load(datas.size());
                datas.addAll(load);
                return load;
            }
        });
        // 第二个参数 慢慢滑动的时候是否加载图片false 加载 true 不加载
        // 第三个参数 快速滑动的时候是否加载图片false 加载 true 不加载
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        return listView;
    }
}

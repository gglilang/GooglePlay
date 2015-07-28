package com.lang.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lang.googleplay.R;
import com.lang.googleplay.adapter.ListBaseAdapter;
import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.holder.HomePictureHolder;
import com.lang.googleplay.protocol.HomeProtocol;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.view.BaseListView;
import com.lang.googleplay.view.LoadingPage;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfo> datas;
    private List<String> pictures;


    // 当Fragment挂载的Activity创建时候调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {
        HomeProtocol protocol = new HomeProtocol();
        datas = protocol.load(0);
        pictures = protocol.getPictures();
        return checkData(datas);

    }



    public View createSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());

        HomePictureHolder holder = new HomePictureHolder();
        holder.setData(pictures);
        View contentView = holder.getContentView();
        listView.addHeaderView(contentView);

        listView.setAdapter(new ListBaseAdapter(datas) {
            @Override
            protected List<AppInfo> onLoad() {
                HomeProtocol protocol = new HomeProtocol();
                List<AppInfo> load = protocol.load(datas.size());
                datas.addAll(load);
                return load;
            }
        });
        // 第二个参数 慢慢滑动的时候是否加载图片false 加载 true 不加载
        // 第三个参数 快速滑动的时候是否加载图片false 加载 true 不加载
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default); // 设置加载时的图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);  // 加载失败时的图片
        return listView;
    }
}

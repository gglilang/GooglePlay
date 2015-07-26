package com.lang.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.utils.BitmapHelper;
import com.lang.googleplay.utils.ViewUtils;
import com.lang.googleplay.view.LoadingPage;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public abstract class BaseFragment extends Fragment {


    private LoadingPage loadingPage;

    public BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // frameLayout复用
        if(loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()) {
                @Override
                public LoadResult load() {
                    return BaseFragment.this.load();
                }

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };
        } else {
            ViewUtils.removeParent(loadingPage);
        }

//        show();

        return loadingPage;
    }

    public void show(){
        if(loadingPage != null){
            loadingPage.show();
        }
    }

    public abstract LoadingPage.LoadResult load();

    public abstract View createSuccessView();


    /**
     * 检测数据
     * @param data
     * @return
     */
    public LoadingPage.LoadResult checkData(List data){
        if(data == null){
            return LoadingPage.LoadResult.error;
        } else {
            if(data.size() == 0){
                return LoadingPage.LoadResult.empty;
            } else {
                System.out.println(data);
                return LoadingPage.LoadResult.success;
            }
        }
    }
}

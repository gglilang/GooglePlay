package com.lang.googleplay.fragment;


import android.view.View;

import com.lang.googleplay.view.LoadingPage;

/**
 * Created by Lang on 2015/7/23.
 */
public class TopFragment extends BaseFragment {

    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}

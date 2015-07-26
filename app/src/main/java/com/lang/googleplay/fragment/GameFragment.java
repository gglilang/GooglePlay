package com.lang.googleplay.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lang.googleplay.view.LoadingPage;

/**
 * Created by Lang on 2015/7/23.
 */
public class GameFragment extends BaseFragment {

    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}

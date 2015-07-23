package com.lang.googleplay.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.Utils.ViewUtils;

/**
 * Created by Lang on 2015/7/23.
 */
public class HomeFragment extends Fragment {

    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;

    private static int state = STATE_UNKOWN;


    private FrameLayout frameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // frameLayout复用
        if(frameLayout == null) {
            frameLayout = new FrameLayout(getActivity());
            init();
        } else {
            ViewUtils.removeParent(frameLayout);
        }

        show();

        return frameLayout;
    }

    private View loadingView;   // 加载中的界面
    private View errorView; // 错误界面
    private View emptyView; // 空界面
    private View successView;   // 成功界面

    /**
     * 在FrameLayout中添加4种不同的界面
     */
    private void init() {
        loadingView = createLoadingView();  // 创建了加载中的界面
        if(loadingView != null){
            frameLayout.addView(loadingView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        errorView = createErrorView();  // 创建了错误的界面
        if(errorView != null){
            frameLayout.addView(errorView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        emptyView = createEmptyView();  // 创建了空的界面
        if(emptyView != null){
            frameLayout.addView(emptyView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();
    }

    private enum LoadResult{
        error(2), empty(3), success(4);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 根据服务器的数据切换状态
     */
    private void show() {
        if(state == STATE_ERROR | state == STATE_EMPTY){
            state = STATE_UNKOWN;
        }
        // 请求服务器 获取服务器上的数据 进行判断
        // 请求服务器 返回一个结果
        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
                final LoadResult result = load();
                if(getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (result != null) {
                                state = result.getValue();

                                showPage(); // 状态改变了，重新判断当前应该显示哪个界面
                            }
                        }
                    });
                }
            }
        }.start();

        showPage();
    }

    private LoadResult load() {
        return LoadResult.success;
    }

    /**
     * 根据不同的状态显示不同的界面
     */
    private void showPage() {
        if(loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN | state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if(errorView != null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if(emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if(state == STATE_SUCCESS){
            successView = createSuccessView();
            if(successView != null){
                frameLayout.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            }
        }
    }

    private View createSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText("加载成功了。。。");
        textView.setTextSize(30);
        return textView;
    }

    private View createEmptyView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_empty, null);
        return view;
    }

    private View createErrorView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_error, null);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

    private View createLoadingView() {
        View view = View.inflate(getActivity(), R.layout.loadpage_loading, null);
        return view;
    }




}

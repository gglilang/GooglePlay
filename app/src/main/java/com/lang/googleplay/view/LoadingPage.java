package com.lang.googleplay.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lang.googleplay.R;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.manager.ThreadManager;

/**
 * 创建了自定义帧布局 把baseFragment一部分代码 抽取到这个类中
 * Created by Lang on 2015/7/25.
 */
public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;

    private View loadingView;   // 加载中的界面
    private View errorView; // 错误界面
    private View emptyView; // 空界面
    private View successView;   // 成功界面

    private int state = STATE_UNKNOWN;

    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 在FrameLayout中添加4种不同的界面
     */
    private void init() {
        loadingView = createLoadingView();  // 创建了加载中的界面
        if (loadingView != null) {
            this.addView(loadingView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        errorView = createErrorView();  // 创建了错误的界面
        if (errorView != null) {
            this.addView(errorView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        emptyView = createEmptyView();  // 创建了空的界面
        if (emptyView != null) {
            this.addView(emptyView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();
    }

    /**
     * 根据服务器的数据切换状态
     */
    public void show() {
        if (state == STATE_ERROR | state == STATE_EMPTY) {
            state = STATE_UNKNOWN;
        }
        // 请求服务器 获取服务器上的数据 进行判断
        // 请求服务器 返回一个结果
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            state = result.getValue();

                            showPage(); // 状态改变了，重新判断当前应该显示哪个界面
                        }
                    }
                });
            }
        });

        showPage();
    }

    public enum LoadResult {
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
     * 根据不同的状态显示不同的界面
     */
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKNOWN | state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                this.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            }
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    private View createEmptyView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_empty, null);
        return view;
    }

    private View createErrorView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_error, null);
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
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_loading, null);
        return view;
    }

    public abstract LoadResult load();

    public abstract View createSuccessView();
}

package com.lang.googleplay.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.lang.googleplay.R;
import com.lang.googleplay.utils.UiUtils;

/**
 * Created by Lang on 2015/7/26.
 */
public class BaseListView extends ListView {
    public BaseListView(Context context) {
        super(context);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 点击显示的颜色
        setSelector(R.drawable.nothing);

        // 拖曳的颜色
        setCacheColorHint(R.drawable.nothing);

        // 每个条目的间隔
        setDivider(UiUtils.getDrawable(R.drawable.nothing));

    }


}

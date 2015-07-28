package com.lang.googleplay.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.bean.SubjectInfo;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.utils.BitmapHelper;
import com.lang.googleplay.utils.UiUtils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by Lang on 2015/7/27.
 */
public abstract class BaseHolder<Data> {

    private View contentView;
    private Data data;
    protected BitmapUtils bitmapUtils;

    public BaseHolder() {
        bitmapUtils = BitmapHelper.getBitmapUtils();
        contentView = initView();
        contentView.setTag(this);
    }

    /**
     * BaseHolder生成对象是，执行该方法
     * @return
     */
    public abstract View initView();

    public View getContentView() {
        return contentView;
    }

    /**
     * 当setData()时，调用此方法
     * @param data
     */
    public abstract void refreshView(Data data);

    public void setData(Data data) {
        this.data = data;
        refreshView(data);
    }
}

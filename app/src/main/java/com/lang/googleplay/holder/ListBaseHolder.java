package com.lang.googleplay.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.utils.UiUtils;

/**
 * Created by Lang on 2015/7/27.
 */
public class ListBaseHolder extends BaseHolder<AppInfo> {
    ImageView item_icon;
    TextView item_title, item_size, item_bottom;
    RatingBar item_rating;
    private View contentView;
    private AppInfo data;

    @Override
    public View initView() {
        contentView = View.inflate(UiUtils.getContext(), R.layout.item_app, null);
        this.item_title = (TextView) contentView.findViewById(R.id.item_title);
        this.item_size = (TextView) contentView.findViewById(R.id.item_size);
        this.item_bottom = (TextView) contentView.findViewById(R.id.item_bottom);
        this.item_icon = (ImageView) contentView.findViewById(R.id.item_icon);
        this.item_rating = (RatingBar) contentView.findViewById(R.id.item_rating);
        return contentView;
    }

    @Override
    public void refreshView(AppInfo data) {
        this.data = data;
        this.item_title.setText(data.getName());
        this.item_size.setText(Formatter.formatFileSize(UiUtils.getContext(), data.getSize()));
        this.item_bottom.setText(data.getDes());
        this.item_rating.setRating(data.getStars());
        String iconUrl = data.getIconUrl();
        bitmapUtils.display(this.item_icon, HttpHelper.URL + "image?name=" + iconUrl);
    }
}

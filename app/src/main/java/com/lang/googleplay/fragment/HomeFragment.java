package com.lang.googleplay.fragment;

import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.protocol.HomeProtocol;
import com.lang.googleplay.utils.BitmapHelper;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.view.BaseListView;
import com.lang.googleplay.view.LoadingPage;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public class HomeFragment extends BaseFragment {

    private List<AppInfo> datas;


    // 当Fragment挂载的Activity创建时候调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {
        HomeProtocol protocol = new HomeProtocol();
        datas = protocol.load(0);
        return checkData(datas);

    }



    public View createSuccessView() {
        BaseListView listView = new BaseListView(UiUtils.getContext());
        listView.setAdapter(new HomeAdapter());
        // 第二个参数 慢慢滑动的时候是否加载图片false 加载 true 不加载
        // 第三个参数 快速滑动的时候是否加载图片false 加载 true 不加载
        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils, false, true));
        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default); // 设置加载时的图片
        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);  // 加载失败时的图片
        return listView;
    }


    private class HomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public AppInfo getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View view;
            if(convertView == null){
                view = View.inflate(UiUtils.getContext(), R.layout.item_app, null);
                holder = new ViewHolder();
                holder.item_title = (TextView) view.findViewById(R.id.item_title);
                holder.item_size = (TextView) view.findViewById(R.id.item_size);
                holder.item_bottom = (TextView) view.findViewById(R.id.item_bottom);
                holder.item_icon = (ImageView) view.findViewById(R.id.item_icon);
                holder.item_rating = (RatingBar) view.findViewById(R.id.item_rating);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            AppInfo item = getItem(position);
            holder.item_title.setText(item.getName());
            holder.item_size.setText(Formatter.formatFileSize(UiUtils.getContext(), item.getSize()));
            holder.item_bottom.setText(item.getDes());
            holder.item_rating.setRating(item.getStars());
            String iconUrl = item.getIconUrl();
            bitmapUtils.display(holder.item_icon, HttpHelper.URL + "image?name=" + iconUrl);

            return view;
        }
    }

    private static class ViewHolder{
        ImageView item_icon;
        TextView item_title, item_size, item_bottom;
        RatingBar item_rating;
    }
}

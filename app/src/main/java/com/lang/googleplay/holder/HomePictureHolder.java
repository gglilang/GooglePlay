package com.lang.googleplay.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.utils.UiUtils;

import java.util.List;

/**
 * Created by Lang on 2015/7/28.
 */
public class HomePictureHolder extends BaseHolder<List<String>> {
    private ViewPager viewPager;
    private List<String> picutres;
    @Override
    public View initView() {
        FrameLayout frameLayout = new FrameLayout(UiUtils.getContext());
        frameLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,240));
        viewPager = new ViewPager(UiUtils.getContext());
        viewPager.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 240));
        frameLayout.addView(viewPager);
        return frameLayout;
    }

    @Override
    public void refreshView(List<String> strings) {
        picutres = strings;
        viewPager.setAdapter(new HomeAdapter());
    }

    class HomeAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return picutres.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(UiUtils.getContext());
            bitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + picutres.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}

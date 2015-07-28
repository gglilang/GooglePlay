package com.lang.googleplay.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lang.googleplay.adapter.DefaultAdapter;
import com.lang.googleplay.R;
import com.lang.googleplay.bean.SubjectInfo;
import com.lang.googleplay.holder.BaseHolder;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.protocol.SubjectProtocol;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.view.BaseListView;
import com.lang.googleplay.view.LoadingPage;

import java.util.List;

/**
 * Created by Lang on 2015/7/23.
 */
public class SubjectFragment extends BaseFragment {

    private List<SubjectInfo> datas;

    @Override
    public LoadingPage.LoadResult load() {
        SubjectProtocol protocol = new SubjectProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }

    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(getActivity());
        listView.setAdapter(new SubjectAdapter(datas));
        return listView;
    }

    private class SubjectAdapter extends DefaultAdapter<SubjectInfo> {
        public SubjectAdapter(List<SubjectInfo> datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new SubjectHolder();
        }

        @Override
        protected List<SubjectInfo> onLoad() {
            SubjectProtocol protocol = new SubjectProtocol();
            List<SubjectInfo> load = protocol.load(datas.size());
            datas.addAll(load);
            return load;
        }
    }

    private static class SubjectHolder extends BaseHolder<SubjectInfo>{
        ImageView item_icon;
        TextView item_txt;
        private View contentView;
        @Override
        public View initView() {
            contentView = UiUtils.inflate(R.layout.item_subject);
            this.item_txt = (TextView) contentView.findViewById(R.id.item_txt);
            this.item_icon = (ImageView) contentView.findViewById(R.id.item_icon);
            return contentView;
        }

        public void refreshView(SubjectInfo data){
            String txt = data.getDes();
            String url = data.getUrl();

            this.item_txt.setText(txt);
            bitmapUtils.display(this.item_icon, HttpHelper.URL + "image?name=" + url);
        }
    }
}

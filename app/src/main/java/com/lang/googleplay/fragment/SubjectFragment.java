package com.lang.googleplay.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lang.googleplay.R;
import com.lang.googleplay.bean.SubjectInfo;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.protocol.SubjectProtocol;
import com.lang.googleplay.utils.UiUtils;
import com.lang.googleplay.view.BaseListView;
import com.lang.googleplay.view.LoadingPage;

import java.security.PrivateKey;
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
        listView.setAdapter(new SubjectAdapter());
        return listView;
    }

    private class SubjectAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public SubjectInfo getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if(convertView == null){
                view = UiUtils.inflate(R.layout.item_subject);
                holder = new ViewHolder();
                holder.item_txt = (TextView) view.findViewById(R.id.item_txt);
                holder.item_icon = (ImageView) view.findViewById(R.id.item_icon);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            String txt = getItem(position).getDes();
            String url = getItem(position).getUrl();

            holder.item_txt.setText(txt);
            bitmapUtils.display(holder.item_icon, HttpHelper.URL + "image?name=" + url);
            return view;
        }
    }

    private static class ViewHolder{
        ImageView item_icon;
        TextView item_txt;
    }
}

package com.lang.googleplay.protocol;

import com.lang.googleplay.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lang on 2015/7/25.
 */
public class AppProtocol extends BaseProtocol<List<AppInfo>>{
    // 解析json数据
    // 见到大括号就用JsonObject，剪刀中括号就是JsonArray
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();
        try {
            JSONArray listArray = new JSONArray(json);
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject infoObject = listArray.getJSONObject(i);
                AppInfo appInfo = new AppInfo();
                appInfo.setId(infoObject.getLong("id"));
                appInfo.setName(infoObject.getString("name"));
                appInfo.setPackageName(infoObject.getString("packageName"));
                appInfo.setIconUrl(infoObject.getString("iconUrl"));
                appInfo.setStars((float) infoObject.getDouble("stars"));
                appInfo.setSize(infoObject.getLong("size"));
                appInfo.setDownloadUrl(infoObject.getString("downloadUrl"));
                appInfo.setDes(infoObject.getString("des"));
                // 添加到集合中
                appInfos.add(appInfo);
            }
            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getKey() {
        return "app";
    }


}

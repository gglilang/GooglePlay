package com.lang.googleplay.protocol;

import android.widget.ListView;

import com.lang.googleplay.bean.AppInfo;
import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.utils.FileUtils;
import com.lang.googleplay.utils.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lang on 2015/7/25.
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>>{

    private List<String> pictures;
    // 解析json数据
    // 见到大括号就用JsonObject，剪刀中括号就是JsonArray
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> appInfos = new ArrayList<>();
        pictures = new ArrayList<>();
        try {
            JSONObject rootObject = new JSONObject(json);

            JSONArray pictureArray = rootObject.getJSONArray("picture");
            for (int i = 0; i < pictureArray.length(); i++) {
                pictures.add(pictureArray.getString(i));
            }

            JSONArray listArray = rootObject.getJSONArray("list");
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
        return "home";
    }

    public List<String> getPictures() {
        return pictures;
    }
}

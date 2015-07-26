package com.lang.googleplay.protocol;

import com.lang.googleplay.bean.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lang on 2015/7/26.
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>>{

    @Override
    public List<SubjectInfo> parseJson(String json) {
        List<SubjectInfo> subjectInfos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SubjectInfo subjectInfo = new SubjectInfo();
                subjectInfo.setDes(jsonObject.getString("des"));
                subjectInfo.setUrl(jsonObject.getString("url"));
                subjectInfos.add(subjectInfo);
            }
            return subjectInfos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getKey() {
        return "subject";
    }
}
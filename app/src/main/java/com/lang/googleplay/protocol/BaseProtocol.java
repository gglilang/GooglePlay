package com.lang.googleplay.protocol;

import android.os.SystemClock;

import com.lang.googleplay.http.HttpHelper;
import com.lang.googleplay.utils.FileUtils;
import com.lang.googleplay.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

/**
 * Created by Lang on 2015/7/25.
 */
public abstract class BaseProtocol<T> {
    public T load(int index) {
        SystemClock.sleep(1000);
        // 请求服务器
        String json = loadLocal(index);
        if (json == null) {
            json = loadServer(index);
            if (json != null) {
                saveLocal(json, index);
            }
        } else {
            System.out.println("复用了本地缓存");
        }

        if (json != null) {
            return parseJson(json);
        } else {
            return null;
        }
    }

    // 解析json数据
    // 见到大括号就用JsonObject，剪刀中括号就是JsonArray
    public abstract T parseJson(String json);

    // 1.把整个json文件写到一个本地文件夹中
    // 2.把每条数据都摘出来保存到数据库中
    private void saveLocal(String json, int index) {
        BufferedWriter bw = null;
        // 在第一行写一个过期时间
        try {
            File dir = FileUtils.getCacheDir();
            File file = new File(dir, getKey() + "_" + index);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 1000 * 10 + "");
            bw.newLine();
            bw.write(json);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(bw);
        }

    }

    private String loadServer(int index) {
        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey() + "?index=" + index);
        String json = null;
        if (httpResult != null) {
            json = httpResult.getString();
//            System.out.println(json);
        }
        return json;
    }

    private String loadLocal(int index) {
        // 如果发现文件已经过期了，就不要再去复用缓存
        // 获取缓存所在的文件夹
        File dir = FileUtils.getCacheDir();
        File file = new File(dir, getKey() + "_" + index);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            long outOfDate = Long.parseLong(br.readLine());
            if (System.currentTimeMillis() > outOfDate) {
                return null;
            } else {
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null) {
                    sw.write(str);
                }
                return sw.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关键字
     * @return
     */
    public abstract String getKey();
}

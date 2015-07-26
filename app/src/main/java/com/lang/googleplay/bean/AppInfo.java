package com.lang.googleplay.bean;

/**
 * Created by Lang on 2015/7/26.
 */
public class AppInfo {
    private long id;
    private String name;
    private String packageName;
    private String iconUrl;
    private float stars;
    private long size;
    private String downloadUrl;
    private String des;

    public AppInfo() {
    }

    public AppInfo(long id, String des, long size, String downloadUrl, float stars, String iconUrl, String packageName, String name) {
        this.id = id;
        this.des = des;
        this.size = size;
        this.downloadUrl = downloadUrl;
        this.stars = stars;
        this.iconUrl = iconUrl;
        this.packageName = packageName;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}

package com.wochacha.learnrxjava.model;

/**
 * Created by guanghui_wan on 2016/12/1.
 */

public class GankBeauty {

    private String createdAt;
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GankBeauty{" +
                "createdAt='" + createdAt + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

package com.wochacha.learnrxjava.model;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public class ZhuangBiImage {

    private String description;
    private String image_url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "ZhuangBiImage{" +
                "description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}

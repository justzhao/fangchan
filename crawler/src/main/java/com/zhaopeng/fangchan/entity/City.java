package com.zhaopeng.fangchan.entity;

/**
 * Created by zhaopeng on 2017/9/9.
 *
 * 杭州二手房  https://hz.lianjia.com/ershoufang/rs/
 *
 * 杭州新房
 *
 * 长沙二手房
 * https://cs.lianjia.com/ershoufang/rs/
 *长沙新房
 *
 */
public class City {
    private String name;
    private String url;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.zhaopeng.fangchan.entity.dto;

/**
 * Created by zhaopeng on 2017/9/9.
 */
public class HouseDTO {
    private String title;// 页面标题
    private String info; // 房子信息
    private String price; // 价格
    private String priceInfo;// 价格信息
    private String averagePrice; //均价
    private String address; // 地址
    private String img; //户型图
    private String area; //面积
    private String basicInfo; //基本信息
    private String tradingInfo; //交易信息
    private String feature;//特点
    private String roomInfo;//房间信息
    private String communityInfo;//小区信息
    private String url; //本房子的url


    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getTradingInfo() {
        return tradingInfo;
    }

    public void setTradingInfo(String tradingInfo) {
        this.tradingInfo = tradingInfo;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getCommunityInfo() {
        return communityInfo;
    }

    public void setCommunityInfo(String communityInfo) {
        this.communityInfo = communityInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package com.zhaopeng.fangchan.house.impl;

import com.zhaopeng.fangchan.entity.CrawlerConstant;
import com.zhaopeng.fangchan.entity.dto.HouseDTO;
import com.zhaopeng.fangchan.processor.LianjiaProcessor;
import com.zhaopeng.fangchan.processor.Processor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by zhaopeng on 2017/9/10.
 */
public class CSErShouPageProcessor implements Processor {


    private static String regexDetail = "https://cs\\.lianjia\\.com/ershoufang/.*\\.html";

    public void processPage(Page page) {

        processUrl(page);

    }

    private void processUrl(Page page) {


        if (page.getUrl().regex(regexDetail).match()) {
            processDetail(page);
        } else {
            Html html = page.getHtml();
            // 获取详情页面的url
            List<String> details = html.xpath("//a[@class=\"title\"]").links().regex(regexDetail).all();
            page.addTargetRequests(details);
            List<String> nextUrls = html.xpath("//div[@class=\"pagination_group_a\"]").links().all();
            page.addTargetRequests(nextUrls);
        }
    }

    private void processDetail(Page page) {
        //https://cs.lianjia.com/ershoufang/104100562293.html
        HouseDTO h = new HouseDTO();
        if (page == null || page.getHtml() == null) {
            return;
        }
        Html html = page.getHtml();
        if (html != null) {
            HouseDTO houseDTO = new HouseDTO();
            houseDTO.setTitle(getTitle(html));
            houseDTO.setInfo(getInfo(html));
            houseDTO.setPrice(getPrice(html));
            houseDTO.setPriceInfo(getPriceInfo(html));
            houseDTO.setAveragePrice(getAveragePrice(html));
            houseDTO.setAddress(getAddress(html));
            houseDTO.setArea(getArea(html));
            houseDTO.setBasicInfo("");
            houseDTO.setTradingInfo("");
            houseDTO.setFeature("");
            houseDTO.setRoomInfo("");
            houseDTO.setCommunityInfo("");
            houseDTO.setUrl(page.getUrl().get());

        }
        page.putField(CrawlerConstant.HOUSE, h);
    }

    private String getArea(Html html){
        String area = html.xpath("//div[@class=\"area\"]").$("div>div","text").get();
        return area;
    }
    private String getAddress(Html html) {
        StringBuilder builder = new StringBuilder();
        Selectable aroundInfo = html.xpath("//div[@class=\"aroundInfo\"]");
        String name = aroundInfo.xpath("//div[@class=\"communityName\"]").$("div>a", "text").get();
        builder.append(name);
        builder.append(",");
        List<Selectable> info = aroundInfo.xpath("//div[@class=\"areaName\"]").xpath("//span[@class=\"info\"]").$("span > a").nodes();
        for(Selectable item:info){
            builder.append(item.$("a","text"));
            builder.append(",");

        }

        return builder.toString();
    }

    private String getAveragePrice(Html html) {

        String price = html.xpath("//div[@class=\"price\"]").xpath("//span[@class=\"unitPriceValue\"]").$("span", "text").get();
        return price;
    }

    private String getPriceInfo(Html html) {
        String priceInfo = html.xpath("//div[@class=\"price\"]").get();
        return priceInfo;
    }

    private String getPrice(Html html) {

        String price = html.xpath("//div[@class=\"price\"]").$("div>span", "text").get();

        return price;
    }

    private String getInfo(Html html) {
        StringBuilder builder = new StringBuilder();
        Selectable selectable = html.xpath("//div[@class=\"houseInfo\"]").$("div>div");
        List<Selectable> houseInfo = selectable.nodes();
        for (Selectable item : houseInfo) {

            builder.append(item.$("div", "text"));
            builder.append(",");

        }
        return builder.toString();
    }

    private String getTitle(Html html) {
        StringBuilder builder = new StringBuilder();
        String title = html.xpath("//div[@class=\"title\"]").$("h1", "text").get();
        builder.append(title);
        String subtitle = html.xpath("//div[@class=\"title\"]").$("div>div", "text").get();
        builder.append(",");
        builder.append(subtitle);
        return builder.toString();
    }


    public static void main(String args[]) {
        String url = "https://cs.lianjia.com/ershoufang/";

        Spider.create(new LianjiaProcessor(new CSErShouPageProcessor())).addUrl(url).thread(1).run();
    }

}

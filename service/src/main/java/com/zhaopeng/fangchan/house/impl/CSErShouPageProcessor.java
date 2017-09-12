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
            houseDTO.setBasicInfo(getBasicInfo(html));
            houseDTO.setTradingInfo(getTradingInfo(html));
            houseDTO.setFeature(getFeature(html));
            houseDTO.setRoomInfo(getRoomInfo(html));
           // houseDTO.setCommunityInfo(getCommunityInfo(html));
            houseDTO.setUrl(page.getUrl().get());

        }
        page.putField(CrawlerConstant.HOUSE, h);
    }

    private String getCommunityInfo(Html html){
            //xiaoqu_content clear  resblockCardContainer
     //   List<Selectable> xiaoqus
                String items=html.xpath("//div[@id=\"resblockCardContainer\"]").get();
                //.xpath("//div[@class=\"xiaoquCard\"]").xpath("//div[@class=\"xiaoqu_main fl\"]").xpath("//div[@class=\"xiaoqu_info\"]").nodes();



        return null;
    }

    private String getRoomInfo(Html html) {
        List<Selectable> rooms = html.xpath("//div[@id=\"layout\"]").xpath("//div[@class=\"content\"]").xpath("//div[@class=\"row\"]").nodes();
        StringBuilder builder = new StringBuilder();
        for (Selectable item : rooms) {
            List<Selectable> cols = item.xpath("//div[@class=\"col\"]").nodes();
            for (Selectable col : cols) {

                builder.append(col.$("div", "text"));
                builder.append(":");
            }
            builder.append(", ");
        }
        return builder.toString();
    }

    private String getFeature(Html html) {
        StringBuilder builder = new StringBuilder();
        Selectable tags = html.xpath("//div[@class=\"introContent showbasemore\"]").xpath("//div[@class=\"tags clear\"]").xpath("//div[@class=\"content\"]");
        builder.append(tags.xpath("//a[@class=\"tag taxfree\"]").$("a", "text"));
        builder.append(",");
        builder.append(tags.xpath("//a[@class=\"tag is_see_free\"]").$("a", "text"));
        List<Selectable> features = html.xpath("//div[@class=\"introContent showbasemore\"]").xpath("//div[@class=\"baseattribute clear\"]").nodes();
        for (Selectable item : features) {
            builder.append(item.xpath("//div[@class=\"name\"]").$("div", "text").get());
            builder.append(",");
            builder.append(item.xpath("//div[@class=\"content\"]").$("div", "text").get());
        }
        return builder.toString();
    }

    private String getTradingInfo(Html html) {
        StringBuilder builder = new StringBuilder();
        List<Selectable> trading = html.xpath("//div[@class=\"transaction\"]").xpath("//div[@class=\"content\"]").$("li").nodes();

        for (Selectable item : trading) {
            builder.append(item.$("li > span", "text").get());
            builder.append(":");
            builder.append(item.$("li", "text"));
            builder.append(",");
        }
        return builder.toString();

    }

    private String getBasicInfo(Html html) {

        StringBuilder builder = new StringBuilder();
        List<Selectable> basics = html.xpath("//div[@class=\"introContent\"]").xpath("//div[@class=\"content\"]").$("li").nodes();

        for (Selectable item : basics) {
            builder.append(item.$("li > span", "text").get());
            builder.append(":");
            builder.append(item.$("li", "text"));
            builder.append(",");
        }
        return builder.toString();

    }

    private String getArea(Html html) {
        String area = html.xpath("//div[@class=\"area\"]").$("div>div", "text").get();
        return area;
    }

    private String getAddress(Html html) {
        StringBuilder builder = new StringBuilder();
        Selectable aroundInfo = html.xpath("//div[@class=\"aroundInfo\"]");
        String name = aroundInfo.xpath("//div[@class=\"communityName\"]").$("div>a", "text").get();
        builder.append(name);
        builder.append(",");
        List<Selectable> info = aroundInfo.xpath("//div[@class=\"areaName\"]").xpath("//span[@class=\"info\"]").$("span > a").nodes();
        for (Selectable item : info) {
            builder.append(item.$("a", "text"));
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

package com.zhaopeng.fangchan.house.impl;

import com.zhaopeng.fangchan.entity.CrawlerConstant;
import com.zhaopeng.fangchan.entity.dto.HouseDTO;
import com.zhaopeng.fangchan.processor.LianjiaProcessor;
import com.zhaopeng.fangchan.processor.Processor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;

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


        page.putField(CrawlerConstant.HOUSE, h);
    }


    public static void main(String args[]) {
        String url = "https://cs.lianjia.com/ershoufang/";

        Spider.create(new LianjiaProcessor(new CSErShouPageProcessor())).addUrl(url).thread(1).run();
    }

}

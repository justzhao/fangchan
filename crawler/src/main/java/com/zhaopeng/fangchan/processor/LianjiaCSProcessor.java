package com.zhaopeng.fangchan.processor;

import com.zhaopeng.fangchan.entity.City;
import com.zhaopeng.fangchan.entity.CrawlerConstant;
import com.zhaopeng.fangchan.entity.dto.HouseDTO;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by zhaopeng on 2017/9/9.
 */
public class LianjiaCSProcessor implements PageProcessor {

    private City city;

    private static String regexDetail = "https://cs\\.lianjia\\.com/ershoufang/.*\\.html";

    private String agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36";


    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent(agent);


    public void process(Page page) {

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
        HouseDTO h = new HouseDTO();
        if(page==null||page.getHtml()==null){
            return;
        }
        Html html=page.getHtml();
        page.putField(CrawlerConstant.HOUSE, h);
    }


    public Site getSite() {
        return site;
    }

    public static void main(String args[]) {
        String url = "https://cs.lianjia.com/ershoufang/";

        Spider.create(new LianjiaCSProcessor()).addUrl(url).thread(1).run();
    }


}

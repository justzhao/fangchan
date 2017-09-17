package com.zhaopeng.fangchan.pipeline;

import com.zhaopeng.fangchan.entity.CrawlerConstant;
import com.zhaopeng.fangchan.entity.EstateInfo;
import com.zhaopeng.fangchan.entity.dto.HouseDTO;
import com.zhaopeng.fangchan.store.PutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.zhaopeng.fangchan.entity.CrawlerConstant.HOUSE;

/**
 * Created by zhaopeng on 2017/9/14.
 */

@Service
public class LianJiaPipeLine implements Pipeline, InitializingBean {


    private Logger logger = LoggerFactory.getLogger(LianJiaPipeLine.class);

    @Autowired
    private PutService putService;


    public void process(ResultItems resultItems, Task task) {

        HouseDTO h = resultItems.get(HOUSE);
        Site site = task.getSite();
        EstateInfo info = new EstateInfo();
        info.setContent(buildContent(h));
        info.setCity(site.getCookies().get(CrawlerConstant.CITY));
        info.setType(site.getCookies().get(CrawlerConstant.TYPE));

        logger.info("process Info {}",resultItems);
        putService.putRequest(info);
    }

    public String buildContent(HouseDTO h) {
        if (h == null) {
            return "";
        }
        StringBuilder content = new StringBuilder();
        content.append(h.getTitle());
        content.append(",");
        content.append(h.getAddress());
        content.append(",");
        content.append(h.getArea());
        content.append(",");
        content.append(h.getPrice());
        content.append(",");
        content.append(h.getPriceInfo());
        content.append(",");
        content.append(h.getAveragePrice());
        content.append(",");
        content.append(h.getBasicInfo());
        content.append(",");
        content.append(h.getTradingInfo());
        content.append(",");
        content.append(h.getRoomInfo());
        content.append(",");
        content.append(h.getCommunityInfo());
        content.append(",");
        content.append(h.getFeature());
        content.append(",");
        content.append(h.getImg());
        content.append(",");
        content.append(h.getInfo());
        content.append(",");
        content.append(h.getUrl());
        content.append(",");


        return content.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        putService.start();
    }
}

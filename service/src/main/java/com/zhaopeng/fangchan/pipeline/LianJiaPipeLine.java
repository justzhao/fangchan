package com.zhaopeng.fangchan.pipeline;

import com.zhaopeng.fangchan.entity.dto.HouseDTO;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static com.zhaopeng.fangchan.entity.CrawlerConstant.HOUSE;

/**
 * Created by zhaopeng on 2017/9/14.
 */
public class LianJiaPipeLine implements Pipeline {

    public void process(ResultItems resultItems, Task task) {

        HouseDTO h = resultItems.get(HOUSE);


    }
}

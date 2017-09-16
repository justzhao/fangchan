package com.zhaopeng.fangchan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaopeng on 2017/9/16.
 */
public class DateUtil {


    public static String getDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}

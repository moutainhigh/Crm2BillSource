package com.al.nppm.test;

import com.al.nppm.common.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangBaoQiang
 * @ClassName: stringTest
 * @description: TODO
 * @date 2019/7/1115:19
 * @Version 1.0
 */

public class stringTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat yyyyMMddHHmmss_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "2019-12-08 12:23:22";
        Map ordOfferInstMap = new HashMap<>();
        ordOfferInstMap.put("expDate","2019-12-01 00:00:00");
        //取当月第一天
        Date firstDate = DateUtils.getMonthFirstDay(0, 0);
        //获取下月的第一天
        Date lastDate =  DateUtils.getNextMonthFirstDay(new Date());

        boolean flag = isEffectiveDate(df.parse(String.valueOf(ordOfferInstMap.get("expDate"))), firstDate, lastDate);
        Date expDate = df.parse(String.valueOf(ordOfferInstMap.get("expDate")));
        if (expDate.after(firstDate) && flag) {
            ordOfferInstMap.put("expDate", lastDate);
        }
        System.out.println(yyyyMM.parse(str));

    }
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        /*if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }*/

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}

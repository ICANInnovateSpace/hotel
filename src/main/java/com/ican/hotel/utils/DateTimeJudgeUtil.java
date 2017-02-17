package com.ican.hotel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

/**
 * Created by mrzhou on 17-2-17.
 * 订房时间计算与判断的工具类
 */
public class DateTimeJudgeUtil {
    public static final int S = 1000;
    public static final int M = 60 * S;
    public static final int H = 60 * M;
    public static final int D = 24 * H;

    /**
     * 根据下单日期和订房天数算出退房日期
     * 计算规则：订房的时间若在12点前，则
     * 到当天12点算作一天。
     *
     * @param orderDate 下单的日期
     * @param orderTime 下单的时间
     * @param days 订房天数
     * @return 退房日期
     * */
    public static String getQuitDate(String orderDate,String orderTime,String days) throws ParseException {
        //日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat quitDateFormat = new SimpleDateFormat("yyyy-MM-dd 12:00:00");
        //下单日期
        Date parseDate = simpleDateFormat.parse(orderDate + " " + orderTime);
        //用于判断下单时间是否在12点之前
        Date curruntDate = simpleDateFormat.parse(orderDate + " 12:00");
        //将字符串类型的天数转换成int类型
        int day = Integer.parseInt(days);
        //判断是否在12点前下单，12点前下单的话，到当天12点算作一天
        if (parseDate.getTime() < curruntDate.getTime()){
            day -= 1;
        }
        long quit = parseDate.getTime() + day * D;
        //算出退房的日期
        String quitDateString = quitDateFormat.format(new Date(quit));
        return quitDateString;
    }

    public static void main(String[] args) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(format);
    }
}

package com.DateTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @BelongsProhect:ssss
 * @BelongsPackage:com.DateTest
 * @Auther:Chen
 * @CreateTime:2022--07--01 21--48
 * @Decription:T000
 */
public class dateTest {
    public static void main(String[] args) {
        //偏移量new Date(Year-1900,Month-1,Day)
//        Date date = new Date(2022-1900,7-1,1);
//        System.out.println(date);
        //获取当前日期、时间、日期+时间
//        LocalDate localDate = LocalDate.now();
//        LocalTime localTime = LocalTime.now();
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDate);
//        System.out.println(localTime);
//        System.out.println(localDateTime);
        //of( )：设置指定年月日时分秒。没有偏移量
//        LocalDateTime localDateTime = LocalDateTime.of(2001,2,24,6,15,30,140);
//        System.out.println(localDateTime);
//
//        LocalDateTime localDateTime1 = localDateTime.plusMonths(3);
//        System.out.println(localDateTime1);

        //获得的是本初子午线上的时刻
//        Instant instant = Instant.now( );
        //添加时间的偏移量【东八区】
//        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
//        System.out.println(offsetDateTime);
//        //获取自1970年1月1日0时0分0秒开始的毫秒数
//        long milli = instant.toEpochMilli();
//        System.out.println(milli);
//        //ofEpochMilli()：根据毫秒数获取指定的Instant实例 ----> Date类的时间
//        Instant instant1 = Instant.ofEpochMilli(milli);
//        System.out.println(instant1);


        //方式一：预定义的标准格式。
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            //格式化：日期-->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format((localDateTime));
        System.out.println(localDateTime);//2022-07-01T22:46:08.467
        System.out.println(str1);//2022-07-01T22:46:08.467

            //解析：字符串-->日期
        TemporalAccessor parse = formatter.parse("2022-07-01T22:39:16.709");
        System.out.println(parse); //{},ISO resolved to 2022-07-01T22:39:16.709

        //方式二：本地化相关的格式。ofLocalizedDateTime()
            DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
            //格式化
            String str2 = formatter1.format(localDateTime);
            System.out.println(str2); //2022-7-1 22:46:08

            DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            //格式化
            String str3 = formatter2.format(LocalDateTime.now());
            System.out.println(str3); //2022-7-1

        //方式三：自定义的格式。
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            //格式化
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4); //2022-07-01 10:54:04
            //解析
        TemporalAccessor accessor = formatter3.parse("2022-07-01 10:54:04");
        System.out.println(accessor);
        //{NanoOfSecond=0, MicroOfSecond=0, SecondOfMinute=4,HourOfAmPm=10,
        // MinuteOfHour=54, MilliOfSecond=0},ISO resolved to 2022-07-01



    }


}

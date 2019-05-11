package com.whitetest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringUtils {

    /* 由 2018-07-17 格式的字符串获取 date */
    public static Date StringToTime(String timestr) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(timestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /* 将date类型转化为 格式 2018-07-18 的string */
    public static String DateToString(Date date) {
        String date_str;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date_str = sdf.format(date);
        return date_str;
    }

    public static Date CalendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    /* 获取当天的日期，格式 2018-07-18 */
    public static String getCurrentDate() {
        String current_date;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        current_date = simpleDateFormat.format(date);
        return current_date;
    }

    /* 让月份保持为两位的字符串 */
    public static String getDoubleDigitMonth(int month) {
        String monthstr;
        if (month + 1 < 10) {
            int m = month + 1;
            monthstr = "0" + m;
        } else {
            int m = month + 1;
            monthstr = "" + m;
        }
        return monthstr;
    }

    /* 让日期保持为两位的字符串 */
    public static String getDoubleDigitDay(int day) {
        String daystr;
        if (day < 10) {
            daystr = "0" + day;
        } else
            daystr = "" + day;
        return daystr;
    }



    /* calendar转化成 2018-07-18 格式的字符串 */
    public static String CalendarToString(Calendar calendar){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 设置你想要的格式
        return dateFormat.format(calendar.getTime());
    }

    /* 2018-07-17 格式的字符串转化成 calendar */
    public static Calendar StringToCalendar(String datestr){
        Calendar calendar = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(datestr); //start_date是类似"2013-02-02"的字符串
            calendar.setTime(date);
        } catch (Exception e){
            e.printStackTrace();
        }
        return calendar;
    }

    /* 由string判断是否是双休日 */
    public static boolean isWeekend(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return (day == Calendar.SATURDAY || day == Calendar.SUNDAY);
    }
    /* 由string判断是否是节假月 是否是二月，七月，八月 */
    public static boolean isHoilday(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int month = calendar.get(Calendar.MONTH);
        return (month == Calendar.FEBRUARY || month == Calendar.AUGUST
                || month == Calendar.JULY);
    }

    /* 由string判断是否是法定节假日 */
    public static String isLegalHoliday(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        LunarUtils lunar = LunarUtils.getLunarDate(datestr);
        String lunar_month = LunarUtils.getChinaMonthString(lunar.getLunarMonth());
        String lunar_day = LunarUtils.getChinaDayString(lunar.getLunarDay());

        if (month == Calendar.JANUARY && day == 1){
            return "元旦";
        } else if (lunar_month.equals("腊月") && lunar_day.equals("三十")){
            return "除夕";
        } else if (lunar_month.equals("正月") && (lunar_day.equals("初一") || lunar_day.equals("初二"))){
            return "春节";
        } else if (month == Calendar.APRIL && day == 5){
            return "清明";
        } else if (month == Calendar.MAY && day == 1){
            return "劳动节";
        } else if (lunar_month.equals("五月") && lunar_day.equals("初五")){
            return "端午";
        } else if (lunar_month.equals("八月") && lunar_day.equals("十五")){
            return "中秋";
        } else if (month == Calendar.OCTOBER && (day >= 1 && day <= 3)){
            return "国庆";
        } else {
            return "无";
        }
    }

}

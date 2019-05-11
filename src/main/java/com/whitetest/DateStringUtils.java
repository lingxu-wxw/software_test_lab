package com.whitetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringUtils {

    static private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *  String类型和java.util.Date类型互相转化
     *  @sample datestr = 2018-07-17
     */
    public static Date StringToDate(String datestr) {
        Date date = new Date();
        try {
            date = sdf.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToString(Date date) {
        String datestr;
        datestr = sdf.format(date);
        return datestr;
    }

    /**
     *  String类型和java.util.Calendar类型互相转化
     *  @sample datestr = 2018-07-17
     */
    public static Calendar StringToCalendar(String datestr){
        Calendar calendar = new GregorianCalendar();
        try {
            Date date = sdf.parse(datestr);
            calendar.setTime(date);
        } catch (Exception e){
            e.printStackTrace();
        }
        return calendar;
    }

    public static String CalendarToString(Calendar calendar){
        return sdf.format(calendar.getTime());
    }

    /**
     *  java.util.Date类型和java.util.Calendar类型互相转化
     */
    public static Date CalendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     *  String类型和java.util.Calendar类型互相转化
     *  @sample datestr = 2018-07-17
     */
    public static String getCurrentDate() {
        String current_date;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        current_date = simpleDateFormat.format(date);
        return current_date;
    }

    /**
     *  int类型的month和day位数修整
     */
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

    public static String getDoubleDigitDay(int day) {
        String daystr;
        if (day < 10) {
            daystr = "0" + day;
        } else
            daystr = "" + day;
        return daystr;
    }

    /**
     *  判断日期是否为双休日，法定节假日
     *  @param datestr : yyyy-MM-dd
     */
    public static boolean isWeekend(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return (day == Calendar.SATURDAY || day == Calendar.SUNDAY);
    }

    public static boolean isHoilday(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int month = calendar.get(Calendar.MONTH);
        return (month == Calendar.FEBRUARY || month == Calendar.AUGUST
                || month == Calendar.JULY);
    }

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

    /**
     * DateStringUtils类主函数
     */
    public static void main(String[] args) throws ParseException {

    }

}

package com.whitetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  Date和Calendar类 在年月日越界时仍然会返回值
 *  @sample： 2019-5-32 返回 2019-06-01
 *  在输入有错误时返回值不规则
 *  @sample： 2019-5x-11 返回 2019-05-12
 */

public class DateStringUtils {

    static private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final private static String weekdayString[] = {"日", "一", "二", "三", "四", "五", "六"};

    /**
     *  让月份month和日期day保持为两位的字符串
     */
    public static String getDoubleDigitMonth(int month) {
        String monthstr;
        if (month < 10) {
            int m = month;
            monthstr = "0" + m;
        } else {
            int m = month;
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

    public static String DateToChineseString(Date date){
        Calendar calendar = StringToCalendar(DateToString(date));
        return calendar.get(Calendar.YEAR) + "年" + getDoubleDigitMonth(calendar.get(Calendar.MONTH) + 1) + "月"
                + getDoubleDigitDay(calendar.get(Calendar.DAY_OF_MONTH)) + "日";
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
     *  获取当前时间
     */
    public static String getCurrentDate() {
        String current_date;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        current_date = simpleDateFormat.format(date);
        return current_date;
    }

    /**
     * @ description: 比较某个日期和现在日期的先后
     */
    public static boolean beforeDate(String datestr1, String datestr2){
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = sdf.parse(datestr1);
            date2 = sdf.parse(datestr2);
        } catch (Exception e){
            e.printStackTrace();
        }
        return date1.before(date2);
    }

    public static boolean beforeDateIgnoreYear(String datestr1, String datestr2){
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = sdf.parse(datestr1);
            date2 = sdf.parse(datestr2);
            date1.setYear(date2.getYear());
        } catch (Exception e){
            e.printStackTrace();
        }
        return date1.before(date2);
    }

    public static String getWeekday(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return "星期" + weekdayString[day-1];
    }

    public static String getSemester(String datestr){
        if(isSpringSemester(datestr)){
            return "春季学期";
        }else if(isFallSemester(datestr)){
            return "秋季学期";
        }else if(isWinterHoilday(datestr)){
            return "寒假期间";
        }else if(isSummerHoilday(datestr)){
            return "暑假期间";
        }
        assert false;
        return "错误情况";
    }

    public static boolean isSpringSemester(String datestr){
        return (beforeDateIgnoreYear("2019-02-24", datestr) && beforeDateIgnoreYear(datestr, "2019-06-29"));
    }

    public static boolean isFallSemester(String datestr){
        return (beforeDateIgnoreYear("2019-09-09", datestr) || beforeDateIgnoreYear(datestr, "2019-01-17"));
    }

    public static boolean isWinterHoilday(String datestr){
        return (beforeDateIgnoreYear("2019-01-16", datestr) && beforeDateIgnoreYear(datestr, "2019-02-25"));
    }

    public static boolean isSummerHoilday(String datestr){
        return (beforeDateIgnoreYear("2019-06-28", datestr) && beforeDateIgnoreYear(datestr, "2019-09-10"));
    }

    public static String getLegalHoliday(String datestr){
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        LunarDate lunar = LunarDate.getLunarDate(datestr);
        String lunar_month = LunarDate.getChinaMonthString(lunar.getLunarMonth());
        String lunar_day = LunarDate.getChinaDayString(lunar.getLunarDay());

        if (month == Calendar.JANUARY && day == 1){
            return "元旦";
        } else if (lunar_month.equals("腊月") && lunar_day.equals("卅十")){
            return "除夕";
        } else if (lunar_month.equals("正月") && (lunar_day.equals("初一") || lunar_day.equals("初二"))){
            return "春节";
        } else if (month == Calendar.APRIL && day == 5){
            return "清明";
        } else if (month == Calendar.MAY && (day >= 1 && day <= 4)){
            return "劳动节";
        } else if (lunar_month.equals("五月") && lunar_day.equals("初五")){
            return "端午";
        } else if (lunar_month.equals("八月") && lunar_day.equals("十五")){
            return "中秋";
        } else if (month == Calendar.OCTOBER && (day >= 1 && day <= 7)){
            return "国庆";
        } else {
            return "无法定假日";
        }
    }
}

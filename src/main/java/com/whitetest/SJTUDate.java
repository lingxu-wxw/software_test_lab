package com.whitetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Allen
 * @date 2019/5/11 22:34
 */
public class SJTUDate {
    Date solar_date;
    LunarDate lunar_date;
    String weekday;
    String holiday;
    String semester;

    public SJTUDate(String datestr){
        solar_date = DateStringUtils.StringToDate(datestr);
        lunar_date = LunarDate.getLunarDate(datestr);
        weekday = DateStringUtils.getWeekday(datestr);
        holiday = DateStringUtils.getLegalHoliday(datestr);
        semester = DateStringUtils.getSemester(datestr);
    }

    public String toString(){
        return DateStringUtils.DateToChineseString(solar_date) + " "
                + lunar_date.toString() + " " + weekday + " "
                + holiday + " " + semester + "\n";
    }

    public static void main(String[] args){
        System.out.print("请输入日期:");
        Scanner sc = new Scanner(System.in);
        String datestr = sc.nextLine();
        SJTUDate sjtuDate = new SJTUDate(datestr);
        System.out.print(sjtuDate.toString());
    }
}

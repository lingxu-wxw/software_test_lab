package com.whitetest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LunarDateTest {

    private String testname = "";
    private static LunarDate lunarDate;

    //仅执行一次，在测试一开始执行
    @BeforeClass
    public static void beforeClass(){
        System.out.println("LunarDate Test Start Here.");
        lunarDate = new LunarDate("2019-5-11");
    }

    @Test
    public void getLunarYear() {
        testname = "getLunarYear";
        assertEquals(2019, lunarDate.getLunarYear());
    }

    @Test
    public void getLunarMonth() {
        testname = "getLunarMonth";
        assertEquals(4, lunarDate.getLunarMonth());
    }

    @Test
    public void getLunarDay() {
        testname = "getLunarDay";
        assertEquals(7, lunarDate.getLunarDay());
    }

    @Test
    public void getAnimalsYear() {
        testname = "getAnimalsYear";
        assertEquals("猪", lunarDate.animalsYear());
    }

    @Test
    public void getCyclical() {
        testname = "getCyclical";
        assertEquals("己亥", lunarDate.cyclical());
    }

    /**
     *  农历最近的四个闰年：
     *  2012年5月21日 闰四月小 壬辰年
     *  2014年10月24日 闰九月小 甲午年
     *  2017年7月23日 闰六月大 丁酉年
     *  2020年5月23日 闰四月小 庚子年
     *
     *  公历最近的三个闰年：
     *  2020年，2016年，2012年
     */

    // 公历平年 农历平年
    @Test
    public void getLunarDate_1() {
        testname = "getLunarDate_1";
        String lunardate = LunarDate.getLunarDateString("2019-5-11");
        assertEquals("二〇一九年四月初七 猪 己亥年", lunardate);
    }

    // 公历平年 农历闰年 闰月29天
    @Test
    public void getLunarDate_2() {
        testname = "getLunarDate_2";
        String lunardate = LunarDate.getLunarDateString("2014-10-24");
        assertEquals("二〇一四年闰九月初一 马 甲午年", lunardate);
    }

    @Test
    public void getLunarDate_3() {
        testname = "getLunarDate_3";
        String lunardate = LunarDate.getLunarDateString("2014-11-22");
        assertEquals("二〇一四年十月初一 马 甲午年", lunardate);
    }

    // 公历平年 农历闰年 闰月30天
    @Test
    public void getLunarDate_4() {
        testname = "getLunarDate_4";
        String lunardate = LunarDate.getLunarDateString("2017-7-23");
        assertEquals("二〇一七年闰六月初一 鸡 丁酉年", lunardate);
    }

    @Test
    public void getLunarDate_5() {
        testname = "getLunarDate_5";
        String lunardate = LunarDate.getLunarDateString("2017-8-21");
        assertEquals("二〇一七年闰六月卅十 鸡 丁酉年", lunardate);
    }

    // 公历闰年 农历平年
    @Test
    public void getLunarDate_6() {
        testname = "getLunarDate_6";
        String lunardate = LunarDate.getLunarDateString("2016-2-29");
        assertEquals("二〇一六年正月廿二 猴 丙申年", lunardate);
    }

    // 公历闰年 农历闰年 闰月29天
    @Test
    public void getLunarDate_7() {
        testname = "getLunarDate_7";
        String lunardate = LunarDate.getLunarDateString("2012-5-21");
        assertEquals("二〇一二年闰四月初一 龙 壬辰年", lunardate);
    }

    @Test
    public void getLunarDate_8() {
        testname = "getLunarDate_8";
        String lunardate = LunarDate.getLunarDateString("2012-12-21");
        assertEquals("二〇一二年十一月初九 龙 壬辰年", lunardate);
    }

    // 公历闰年 农历闰年 闰月30天 (这种情况近几十年都没有出现)

    //每次测试方法执行结束时，该方法执行一次
    @After
    public void after(){
        System.out.println("Test " + testname + " ...");
    }

    //仅执行一次，在测试结束执行
    @AfterClass
    public static void afterClass(){
        System.out.println("DateStringUtils Test Finish Here.");
    }

}
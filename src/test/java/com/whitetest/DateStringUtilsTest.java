package com.whitetest;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringUtilsTest {

    private String testname = "";

    //仅执行一次，在测试一开始执行
    @BeforeClass
    public static void beforeClass(){
        System.out.println("DateStringUtils Test Start Here.");
    }

    @Test
    public void getDoubleDigitMonth_1() {
        testname = "getDoubleDigitMonth_1";
        assertEquals("05", DateStringUtils.getDoubleDigitMonth(5));
    }

    @Test
    public void getDoubleDigitMonth_2() {
        testname = "getDoubleDigitMonth_2";
        assertEquals("11", DateStringUtils.getDoubleDigitMonth(11));
    }

    @Test
    public void getDoubleDigitDay_1() {
        testname = "getDoubleDigitDay_1";
        assertEquals("05", DateStringUtils.getDoubleDigitDay(5));
    }

    @Test
    public void getDoubleDigitDay_2() {
        testname = "getDoubleDigitDay_2";
        assertEquals("10", DateStringUtils.getDoubleDigitDay(10));
    }

    @Test
    public void stringToDate() {
        testname = "stringToDate";
        Date date = DateStringUtils.StringToDate("2019-5-11");
        String datestr = DateStringUtils.DateToString(date);
        assertEquals("2019-05-11", datestr);
        assertEquals(null, DateStringUtils.StringToDate("2019--11"));
    }

    @Test
    public void stringToDate_chinese() {
        testname = "stringToDate_chinese";
        Date date = DateStringUtils.StringToDate("2019-5-11");
        String datestr = DateStringUtils.DateToChineseString(date);
        assertEquals("2019年05月11日", datestr);
    }

    @Test
    public void stringToCalendar() {
        testname = "stringToCalendar";
        String datestr = "2019-05-09";
        Calendar calendar = DateStringUtils.StringToCalendar(datestr);
        assertEquals(calendar.get(Calendar.YEAR), 2019);
        assertEquals(calendar.get(Calendar.MONTH)+1, 5);
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), 9);
        assertEquals(null, DateStringUtils.StringToCalendar("2019-05"));
    }

    @Test
    public void CalendarToStringTest() {
        testname = "CalendarToString";
        Calendar calendar = new GregorianCalendar();
        calendar.set(2019, Calendar.MAY, 9);
        assertEquals(DateStringUtils.CalendarToString(calendar), "2019-05-09");
    }

    @Test
    public void getCurrentDate() {
        testname = "getCurrentDate";
        assertEquals("2019-05-12", DateStringUtils.getCurrentDate());
    }

    @Test
    public void beforeDate() {
        testname = "beforeDate";
        String datestr1 = "2019-05-11";
        String datestr2 = "2018-07-07";
        boolean isbefore = DateStringUtils.beforeDate(datestr1, datestr2);
        assertEquals(false, isbefore);
        assertEquals(false, DateStringUtils.beforeDate("2019-01", "2019-05-01"));
    }

    @Test
    public void beforeDateIgnoreYear() {
        testname = "beforeDate";
        String datestr1 = "2019-05-11";
        String datestr2 = "2018-07-07";
        boolean isbefore = DateStringUtils.beforeDateIgnoreYear(datestr1, datestr2);
        assertEquals(true, isbefore);
        assertEquals(false, DateStringUtils.beforeDateIgnoreYear("2019-05", "2019-7-12"));
    }

    @Test
    public void getWeekday() {
        testname = "getWeekday";
        String day = DateStringUtils.getWeekday("2019-5-11");
        assertEquals("星期六", day);
        assertEquals("日期格式错误!", DateStringUtils.getWeekday("2019-5"));
    }

    @Test
    public void  isSpringSemesterTest(){
        String datestr = "2019-03-01";
        assertEquals(DateStringUtils.isSpringSemester(datestr), true);
        datestr = "2019-01-01";
        assertEquals(DateStringUtils.isSpringSemester(datestr), false);
    }

    @Test
    public void  isFallSemesterTest(){
        String datestr = "2019-10-01";
        assertEquals(DateStringUtils.isFallSemester(datestr), true);
        datestr = "2019-02-01";
        assertEquals(DateStringUtils.isFallSemester(datestr), false);
    }

    @Test
    public void  isWinterHoildayTest(){
        String datestr = "2019-02-01";
        assertEquals(DateStringUtils.isWinterHoilday(datestr), true);
        datestr = "2019-01-01";
        assertEquals(DateStringUtils.isWinterHoilday(datestr), false);
    }

    @Test
    public void  isSummerHoildayTest(){
        String datestr = "2019-07-01";
        assertEquals(DateStringUtils.isSummerHoilday(datestr), true);
        datestr = "2019-05-09";
        assertEquals(DateStringUtils.isSummerHoilday(datestr), false);
    }

    @Test
    public void getSemester_1() {
        testname = "getSemester_1";
        String semester = DateStringUtils.getSemester("2019-5-11");
        assertEquals("春季学期", semester);
    }

    @Test
    public void getSemester_2() {
        testname = "getSemester_2";
        String semester = DateStringUtils.getSemester("2019-2-4");
        assertEquals("寒假期间", semester);
    }

    @Test
    public void getSemester_3() {
        testname = "getSemester_3";
        String semester = DateStringUtils.getSemester("2019-12-9");
        assertEquals("秋季学期", semester);
    }

    @Test
    public void getSemester_4() {
        testname = "getSemester_4";
        String semester = DateStringUtils.getSemester("2019-7-7");
        assertEquals("暑假期间", semester);
    }

    @Test
    public void getLegalHoliday_1() {
        testname = "getLegalHoliday_1";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-1-1");
        assertEquals("元旦", legalHoliday);
    }

    @Test
    public void getLegalHoliday_2() {
        testname = "getLegalHoliday_2";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-2-4");
        assertEquals("除夕", legalHoliday);
    }

    @Test
    public void getLegalHoliday_3() {
        testname = "getLegalHoliday_3";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-2-5");
        assertEquals("春节", legalHoliday);
    }

    @Test
    public void getLegalHoliday_4() {
        testname = "getLegalHoliday_4";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-4-5");
        assertEquals("清明", legalHoliday);
    }

    @Test
    public void getLegalHoliday_5() {
        testname = "getLegalHoliday_5";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-5-1");
        assertEquals("劳动节", legalHoliday);
    }

    @Test
    public void getLegalHoliday_6() {
        testname = "getLegalHoliday_6";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-6-7");
        assertEquals("端午", legalHoliday);
    }

    @Test
    public void getLegalHoliday_7() {
        testname = "getLegalHoliday_7";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-9-13");
        assertEquals("中秋", legalHoliday);
    }

    @Test
    public void getLegalHoliday_8() {
        testname = "getLegalHoliday_8";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-10-1");
        assertEquals("国庆", legalHoliday);
    }

    @Test
    public void getLegalHoliday_9() {
        testname = "getLegalHoliday_9";
        String legalHoliday = DateStringUtils.getLegalHoliday("2019-5-11");
        assertEquals("无法定假日", legalHoliday);
    }

    @Test
    public void getLegalHoliday_10(){
        assertEquals("日期格式错误!", DateStringUtils.getLegalHoliday("2019-5"));
    }

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
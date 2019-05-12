package com.whitetest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class SJTUDateTest {

    private String testname = "";

    //仅执行一次，在测试一开始执行
    @BeforeClass
    public static void beforeClass(){
        System.out.println("DateStringUtils Test Start Here.");
    }

    @Test
    public void testSJTUDate() {
        testname = "testSJTUDate";
        SJTUDate sjtuDate = new SJTUDate("2019-5-11");
        assertEquals("2019年05月11日 二〇一九年四月初七 猪 己亥年 星期六 无法定假日 春季学期",
                sjtuDate.toString());
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
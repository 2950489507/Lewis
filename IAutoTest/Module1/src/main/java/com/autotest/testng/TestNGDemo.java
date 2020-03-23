package com.autotest.testng;

import org.testng.annotations.*;

public class TestNGDemo {

    //执行顺序  Suite->Test->Class->Method
    //一个测试套件中包含多个测试项Test,一个Test中可以包含多个类，一个类中可以包含多个测试方法
    @Test //被测试的方法
    public void testCase1() {
        System.out.println("This is TestCase1");
    }
    
    @BeforeMethod //在测试方法执行之前执行（每个测试方法执行前都会执行一次）
    public void beforeMethod() {
        System.out.println("Run before the Test Method Everytime!");
    }
    
    @AfterMethod//在测试方法执行之后执行(每个测试方法执行后都会执行一次)
    public void afterMethod() {
        System.out.println("Run after the Test Method Everytime!");
    }
    
    @BeforeClass //在测试方法所在类执行之前执行 
    public void beforeClass() {
        System.out.println("Run before the Class");
    }
    
    @AfterClass //在测试方法所在类执行之后执行
    public void afterClass() {
        System.out.println("Run after the Class");
    }
    
    @BeforeSuite //在测试套件执行之前执行 （在类之前）
    public void beforeSuite() {
        System.out.println("Run before the Suite");
    }
    
    @AfterSuite //在测试套件执行之后执行
    public void afterSuite() {
        System.out.println("Run after the Suite");
    }
    
    @BeforeTest //在套件中的一个测试执行之前执行
    public void beforeTest() {
        System.out.println("run before test");
    }
    @AfterTest //在套件中的一个测试执行之后执行
    public void afterTest() {
        System.out.println("run after test");
    }
}

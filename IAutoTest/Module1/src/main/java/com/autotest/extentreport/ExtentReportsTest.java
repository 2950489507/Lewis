package com.autotest.extentreport;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentReportsTest {

	@Test
	public void test1() {
		Assert.assertEquals(1, 2);
	}
	
	@Test
	public void test2() {
		System.out.println("顶替束带结发");
		Assert.assertEquals(2, 2);
	}
	
	@Test
	public void test3() {
		Assert.assertEquals("aaa", "aaa");
	}
	
	@Test
	public void logDemo() {
		Reporter.log("这是自己记录的日志");
		throw new RuntimeException("运行时异常");
	}
}

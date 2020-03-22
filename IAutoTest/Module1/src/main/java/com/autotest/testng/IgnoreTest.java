package com.autotest.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

	@Test
	public void test1() {
		System.out.println("test1 running");
	}
	
	@Test(enabled = false)  //忽略测试 设置enabled = false，则不会执行
	public void test2() {
		System.out.println("test2 running");
	}
	
	@Test(enabled = true)
	public void test3() {
		System.out.println("test3 running");
	}
}

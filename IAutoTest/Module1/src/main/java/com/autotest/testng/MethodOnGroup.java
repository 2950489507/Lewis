package com.autotest.testng;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class MethodOnGroup {
	
	@BeforeGroups(groups = "group1")//所在组方法运行之前运行
	public void beforeGroup() {
		System.out.println("run before group1");
	}
	@AfterGroups(groups = "group1")//所在组所有方法运行之后运行
	public void afterGroup() {
		System.out.println("run after group1");
	}
	@Test(groups = "group1")
	public void test1() {
		System.out.println("test1 method on group1");
	}
	
	@Test(groups = "group1")
	public void test2() {
		System.out.println("test2 method on group1");
	}
	
	@Test(groups = "group2")
	public void test3() {
		System.out.println("test3 method on group2");
	}
	
	@Test(groups = "group2")
	public void test4() {
		System.out.println("test4 method on group2");
	}
	
}

package com.autotest.testng;

import org.testng.annotations.Test;

@Test(groups = "group3")
public class ClassOnGroup3 {
	
	public void test1() {
		System.out.println("test1 method on group3");
	}
	
	public void test2() {
		System.out.println("test2 method on group3");
	}
}

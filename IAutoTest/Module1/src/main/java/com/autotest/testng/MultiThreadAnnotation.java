package com.autotest.testng;

import org.testng.annotations.Test;

public class MultiThreadAnnotation {

	//该测试方法可在4个线程中并发执行，共被调用10次，执行超时3秒
	@Test(invocationCount = 10,threadPoolSize = 4,timeOut = 3000)
	public void test() {
		System.out.println(Thread.currentThread().getName());
	}
}

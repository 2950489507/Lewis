package com.autotest.testng;

import org.testng.annotations.Test;

public class ExpectedException {
	
	@Test(expectedExceptions = RuntimeException.class)
	public void test() {
		System.out.println("Exception test");
		throw new RuntimeException();
	}
}

package com.autotest.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Run before the Suite");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Run after the Suite");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("run before test");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("run after test");
	}
	
}

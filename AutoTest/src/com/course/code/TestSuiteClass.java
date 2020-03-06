package com.course.code;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({Login.class,BasicAnnotation.class})

public class TestSuiteClass {
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("tessuite:before");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("testsuite:after");
	}

}

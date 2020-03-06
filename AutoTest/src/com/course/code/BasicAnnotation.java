package com.course.code;

import org.junit.*;

public class BasicAnnotation {
	
	@After
	public void after() {
		// TODO Auto-generated method stub
		System.out.println("after method");
	}
	
	@Test
	public void testCase(){
		System.out.println("this is a testcase");
	}
	
	@Test
	public void testCase2(){
		System.out.println("this is a testcase2");
	}
	
	@Before
	public void before() {
		// TODO Auto-generated method stub
		System.out.println("before method");
	}
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("beforeclass output");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("afterclass output");
		
	}

}

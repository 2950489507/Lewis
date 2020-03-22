package com.autotest.testng.suite;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class LoginTest {
	
	@Test(dependsOnGroups = {"group1"})
	public void login() {
		System.out.println("login successfully");
	}
}

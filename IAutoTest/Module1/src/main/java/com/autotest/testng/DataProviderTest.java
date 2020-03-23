package com.autotest.testng;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

	@Test(dataProvider = "DataProvider")
	public void dataProviderTest(String name ,int age) {
		
		System.out.println("name:"+name+";age:"+age);
	}
	
	@DataProvider(name = "DataProvider")
	public Object[][] dataProvider(){
		Object[][] obj =new Object[][]{
			{"zhangsan",19},
			{"lisi",33},
			{"wangwu",32}
		};
		return obj;
 	}
	
	//根据不同方法提供不同数据
	@DataProvider(name = "DataProviderByMethod")
	public Object[][] MethodDataProvider(Method method){
		Object [][] obj=null;
		if(method.getName().equals("test1")) {
			obj=new Object[][] {
				{"lilei",33},
				{"hanmeimei",22}
			};
		}
		if(method.getName().equals("test2")) {
			obj=new Object[][] {
				{"David",31},
				{"Green",21}
			};
		}
		
		return obj;
	}
	
	@Test(dataProvider = "DataProviderByMethod")
	public void test1(String name,int age) {
		System.out.println("test1--name:"+name+";age:"+age);
	}
	
	@Test(dataProvider = "DataProviderByMethod")
	public void test2(String name,int age) {
		System.out.println("test2--name:"+name+";age:"+age);
	}
}

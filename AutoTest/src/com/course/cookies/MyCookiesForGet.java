package com.course.cookies;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.testng.annotations.*;

public class MyCookiesForGet {
	
	private String url;
	private ResourceBundle bundle;
	private CookieStore store;
	@BeforeMethod
	public void before() {
		//获取配置文件中URL信息
		this.bundle=ResourceBundle.getBundle("AutotestConfig", Locale.CHINA);
		this.url=bundle.getString("test.url");
		
	}
	
	@Test(dependsOnMethods={"getCookies"})//HttpClient模拟客户端发送请求并接收数据
	    //从请求接口获取cookies.
	public void getCookies() throws Exception {
		
		store = new BasicCookieStore();
		HttpGet get = new HttpGet(this.url+bundle.getString("getcookies.uri"));
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
		CloseableHttpResponse response = client.execute(get);
		String result = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println(result);
		
		List<Cookie> cookies = store.getCookies();
		
		for(Cookie cookie:cookies){
			String name=cookie.getName();
			String value=cookie.getValue();
			System.out.println("cookies:key="+name+";value="+value);
		}
		
		response.close();
		client.close();
	}
	
	@Test//带cookies get 请求
	public void getWithCookies() throws Exception{
		
		HttpGet get = new HttpGet(url+bundle.getString("get.with.cookies.uri"));
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
		CloseableHttpResponse response = client.execute(get);
		if(store==null){
			System.out.println(1);
		}
		if(response.getCode()==200){
			System.out.println("带cookies请求成功!");
			String result = EntityUtils.toString(response.getEntity(),"utf-8");
			System.out.println(result);
		}
		else {
			System.out.println("带cookies请求失败!");
		}
		
		
		response.close();
		client.close();
	}

}

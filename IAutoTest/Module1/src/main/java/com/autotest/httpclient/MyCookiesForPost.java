package com.autotest.httpclient;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.org.apache.regexp.internal.REUtil;

public class MyCookiesForPost {
	
	private String url;
	private ResourceBundle bundle;
	private CookieStore store;
	@BeforeMethod
	public void before() {
		//获取配置文件中URL信息
		this.bundle=ResourceBundle.getBundle("AutotestConfig", Locale.CHINA);
		this.url=bundle.getString("test.url");
		
	}
	
	@Test //HttpClient模拟客户端发送请求并接收数据
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
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		response.close();
		client.close();
	}
	
	@Test(dependsOnMethods = {"getCookies"})
	public void postWithCookies() throws Exception {
		//声明POST对象
		HttpPost post =new HttpPost(this.url+bundle.getString("post.with.cookies.uri"));
		
		CookieStore store2 = new BasicCookieStore();
		BasicClientCookie bccookie=null;
		for(Cookie cookie:store.getCookies()) {
			String name=cookie.getName();
			String value=cookie.getValue();
			String domain=cookie.getDomain();
			bccookie=new BasicClientCookie(name,value);
			bccookie.setDomain(domain);
			bccookie.setPath("/");
			store2.addCookie(bccookie);
		}
		CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store2).build();
		//添加参数
		JSONObject param = new JSONObject();
		param.put("name", "huhansan");
		param.put("age", "18");
		//设置请求头信息
		post.setHeader("content-type", "application/json");
		//将参数添加到post中
		StringEntity rquest_entity = new StringEntity(param.toString(), "utf-8");
		post.setEntity(rquest_entity);
		
		HttpResponse response = client.execute(post);
		
		HttpEntity response_entity=response.getEntity();
		
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
		String result = EntityUtils.toString(response_entity, "utf-8");
		
		System.out.println("result:"+result);
		
		JSONObject resultJson = new JSONObject(result);
		String status =resultJson.getString("status");
		String message =resultJson.getString("message");
		Assert.assertEquals(status,"1");
		Assert.assertEquals(message,"success");
	}
}

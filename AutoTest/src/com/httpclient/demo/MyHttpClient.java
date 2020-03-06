package com.httpclient.demo;


import java.io.IOException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.*;

import sun.swing.table.DefaultTableCellHeaderRenderer;

public class MyHttpClient {

	@Test
	public void test1() throws Exception{
		HttpGet get = new HttpGet("http://www.baidu.com");
		CloseableHttpClient client = HttpClients.createDefault();
		CloseableHttpResponse response = client.execute(get);
		String result = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(result);

	}
}

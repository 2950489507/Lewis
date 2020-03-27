package com.autotest.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

public class TestConfig {
	
	public static String loginUrl;
	public static String updateUserInfoUrl;
	public static String getUserInfoUrl;
	public static String getUserListUrl;
	public static String addUserUrl;
	
	public static CloseableHttpClient httpclient;
	public static CookieStore store=new BasicCookieStore();
	
}
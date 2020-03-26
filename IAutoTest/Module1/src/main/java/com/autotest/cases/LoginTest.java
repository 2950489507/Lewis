package com.autotest.cases;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.autotest.config.TestConfig;
import com.autotest.model.InterfaceName;
import com.autotest.model.LoginCase;
import com.autotest.utils.ConfigFile;
import com.autotest.utils.DatabaseUtil;

public class LoginTest {

	@BeforeTest( groups = "loginTrue",description = "测试准备工作，获取 HttpClient 对象")
	public void beforeTest() {
		TestConfig.httpclient = HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
	}
	
	 @Test(groups = "loginTrue",description = "用户登录成功接口测试")
	    public void loginTrue() throws IOException {
	        SqlSession session = DatabaseUtil.getSqlSession();
	        LoginCase loginCase = session.selectOne("loginCase",1);
	        System.out.println(loginCase.toString());
	        System.out.println(TestConfig.loginUrl);

	        // 发送请求
	        String result = getResult(loginCase);
	        // 验证结果
	        System.out.println(TestConfig.store);
	        Assert.assertEquals(loginCase.getExpected(),result);
	        
	    }
	 
	  @Test(groups = "loginFalse", description = "用户登录失败接口测试")
	    public void loginFalse() throws IOException {
	        SqlSession session = DatabaseUtil.getSqlSession();
	        LoginCase loginCase = session.selectOne("loginCase",2);
	        System.out.println(loginCase.toString());
	        System.out.println(TestConfig.loginUrl);

	        // 发送请求
	        String result = getResult(loginCase);
	        // 验证结果
	       
	        Assert.assertEquals(loginCase.getExpected(),result);
	        
	    }
	  
	  private String getResult(LoginCase loginCase) throws IOException {
	        HttpPost post = new HttpPost(TestConfig.loginUrl);
	        JSONObject param = new JSONObject();
	        param.put("username",loginCase.getUsername());
	        param.put("password",loginCase.getPassword());
	        
	        post.setHeader("content-type","application/json");
	        StringEntity entity = new StringEntity(param.toString(),"utf-8");
	        post.setEntity(entity);
	        
	        String result;
	        
	        HttpResponse response = TestConfig.httpclient.execute(post);

	        result = EntityUtils.toString(response.getEntity(),"utf-8");
	        //List<Cookie> cookies = TestConfig.store.getCookies();
			
	        return result;
	    }

}

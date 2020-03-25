package com.autotest.cases;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autotest.config.TestConfig;
import com.autotest.model.AddUserCase;
import com.autotest.model.User;
import com.autotest.utils.DatabaseUtil;

public class addUserTest {

	@Test(dependsOnGroups = "loginTrue",description = "添加用户接口测试")
	public void addUser() throws IOException, InterruptedException {
		SqlSession session= DatabaseUtil.getSqlSession();
		AddUserCase addUserCase = session.selectOne("addUserCase", 1);
		System.out.println(addUserCase.toString());
		System.out.println(TestConfig.addUserUrl);
		String result = getResult(addUserCase);
		Thread.sleep(30000);
		User user =session.selectOne("addUser",addUserCase);
		Thread.sleep(10000);
		System.out.println(user.toString());
		Assert.assertEquals(result, addUserCase.getExpected());
		
	}
	
	public String getResult(AddUserCase addUserCase) throws IOException {
		HttpPost post =new HttpPost(TestConfig.addUserUrl);
		JSONObject param=new JSONObject();
		param.put("username",addUserCase.getUsername());
		param.put("password",addUserCase.getPassword());
		param.put("sex",addUserCase.getSex());
		param.put("age",addUserCase.getAge());
		param.put("permission",addUserCase.getPermission());
		param.put("isDelete",addUserCase.getIsDelete());
		
		//设置头信息
		post.setHeader("content-type", "application/json");
		StringEntity entiry=new StringEntity(param.toString(),"utf-8");
		post.setEntity(entiry);
		
		//设置cookies
		TestConfig.httpclient=HttpClients.custom().setDefaultCookieStore(TestConfig.store).build();
		HttpResponse response = TestConfig.httpclient.execute(post);
		String result = EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(result);
		return result;
		
	}
}

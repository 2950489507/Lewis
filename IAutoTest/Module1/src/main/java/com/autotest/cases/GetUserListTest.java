package com.autotest.cases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autotest.config.TestConfig;
import com.autotest.model.GetUserListCase;
import com.autotest.model.User;
import com.autotest.utils.DatabaseUtil;

public class GetUserListTest {

	@Test(dependsOnGroups = "loginTrue",description = "获取用户列表接口测试")
	public void getUserList() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
		GetUserListCase getUserListCase =session.selectOne("getUserListCase", 1);
		
		System.out.println(getUserListCase.toString());
		 System.out.println(TestConfig.getUserListUrl);
		 
		JSONArray jsonArray = getResult(getUserListCase);
		System.out.println(jsonArray.toString());
		List<User> userList=session.selectList(getUserListCase.getExpected(), getUserListCase);
		for(User u:userList) {
			System.out.println("获取的user"+u);
		}
		JSONArray userLsitJson =new JSONArray(userList);
		session.close();
		Assert.assertEquals(jsonArray.length(), userLsitJson.length());
		for(int i=0;i<jsonArray.length();i++) {
			JSONObject expect =(JSONObject)jsonArray.get(i);
			JSONObject actual =(JSONObject)userLsitJson.get(i);
			Assert.assertEquals(actual.toString(),expect.toString());
		}
		
	}

	private JSONArray getResult(GetUserListCase getUserListCase) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpPost post = new HttpPost(TestConfig.getUserListUrl);
		JSONObject param =new JSONObject();
		param.put("username", getUserListCase.getUsername());
		param.put("age", getUserListCase.getAge());
		param.put("sex", getUserListCase.getSex());
		
		post.setHeader("content-type", "application/json");
		StringEntity stringEntity = new StringEntity(param.toString(), "utf-8");
		post.setEntity(stringEntity);
		
		CloseableHttpResponse response =TestConfig.httpclient.execute(post);
		String result =EntityUtils.toString(response.getEntity(), "utf-8");
		JSONArray resultJsonArray = new JSONArray(result);
		return resultJsonArray;
	}
}

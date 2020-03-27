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
import com.autotest.model.GetUserInfoCase;
import com.autotest.model.User;
import com.autotest.utils.DatabaseUtil;

public class GetUserInfoTest {

	@Test(dependsOnGroups = "loginTrue",description = "根据ID获取用户信息接口测试")
	public void getUserInfo() throws IOException {
		
		SqlSession session = DatabaseUtil.getSqlSession();
		GetUserInfoCase getUserInfoCase =session.selectOne("getUserInfoCase", 1);
		System.out.println(getUserInfoCase.toString());
		System.out.println(TestConfig.getUserInfoUrl);
		
		JSONArray resultJson =getJsonResult(getUserInfoCase);
		
		User user=session.selectOne(getUserInfoCase.getExpected(), getUserInfoCase);
		List<User> userList=new ArrayList<User>();
		userList.add(user);
		JSONArray jsonArray=new JSONArray(userList);
		JSONArray jsonArray1=new JSONArray(resultJson.getString(0));
		System.out.println(jsonArray.toString());
		System.out.println(jsonArray1.toString());
		session.close();
		Assert.assertEquals(jsonArray.toString(),jsonArray1.toString());
		
	}
	
	public JSONArray getJsonResult(GetUserInfoCase getUserInfoCase) throws ClientProtocolException, IOException {
		
		HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
		JSONObject param = new JSONObject();
		param.put("id",getUserInfoCase.getUserId());
		post.setHeader("content-type", "application/json");
		
		StringEntity sentity = new StringEntity(param.toString(), "utf-8");
		post.setEntity(sentity);
		String result;
		CloseableHttpResponse response = TestConfig.httpclient.execute(post);
		result= EntityUtils.toString(response.getEntity(),"utf-8");
		System.out.println(result);
		System.out.println(TestConfig.store);
		List<String> resultList = Arrays.asList(result);
		JSONArray array = new JSONArray(resultList);
		return array;
	}
}

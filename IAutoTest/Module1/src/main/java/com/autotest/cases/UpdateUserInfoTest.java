package com.autotest.cases;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.autotest.config.TestConfig;
import com.autotest.model.UpdateUserInfoCase;
import com.autotest.model.User;
import com.autotest.utils.DatabaseUtil;

public class UpdateUserInfoTest {

	@Test(dependsOnGroups = "loginTrue",description = "更新用户接口测试")
	public void updateUserInfo() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
		UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase", 1);
		System.out.println(updateUserInfoCase.toString());
		System.out.println(TestConfig.updateUserInfoUrl);
		
		int result = getResult(updateUserInfoCase);
		session.close();//新更新的数据在同一连接中查询不到
		SqlSession session2 = DatabaseUtil.getSqlSession();
		User user =session2.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
		session2.close();
		Assert.assertNotNull(user);
		Assert.assertTrue(result>0);
		
	}
	


	@Test(dependsOnGroups = "loginTrue",description = "删除用户信息")
	public void deleteUser() throws IOException {
		SqlSession session = DatabaseUtil.getSqlSession();
		UpdateUserInfoCase updateUserInfoCase=session.selectOne("updateUserInfoCase", 2);
		System.out.println(updateUserInfoCase.toString());
		System.out.println(TestConfig.updateUserInfoUrl);
		
		int result = getResult(updateUserInfoCase);
		session.close();
		SqlSession session2 = DatabaseUtil.getSqlSession();
		User user =session2.selectOne(updateUserInfoCase.getExpected(), updateUserInfoCase);
		session2.close();
		Assert.assertNotNull(user);
		Assert.assertTrue(result>0);
	}
	
	private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
		
		HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
		JSONObject param = new JSONObject();
		param.put("id",updateUserInfoCase.getUserId());
		param.put("username", updateUserInfoCase.getUsername());
		param.put("age", updateUserInfoCase.getAge());
		param.put("sex", updateUserInfoCase.getSex());
		param.put("permission", updateUserInfoCase.getPermission());
		param.put("isDelete", updateUserInfoCase.getIsDelete());
		
		post.setHeader("content-type", "application/json");
		StringEntity sEntity=new StringEntity(param.toString(), "utf-8");
		post.setEntity(sEntity);
		
		CloseableHttpResponse response = TestConfig.httpclient.execute(post);
		String result =EntityUtils.toString(response.getEntity(),"utf-8");
		
		return Integer.parseInt(result);
	}
}

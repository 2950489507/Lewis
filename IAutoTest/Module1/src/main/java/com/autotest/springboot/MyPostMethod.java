package com.autotest.springboot;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autotest.bean.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;

@RestController
@Api(value = "/",tags = "这是我的全部POST方法")
public class MyPostMethod {
	
	private Cookie cookie;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ApiOperation(value = "这是一个POST登陆请求",httpMethod = "POST")
	public String login(HttpServletResponse response,
			@RequestParam String username,
			@RequestParam String password) {
		if(username.equals("zhangsan")&&password.equals("123456")) {
			cookie=new Cookie("login", "true");
			response.addCookie(cookie);
			return "恭喜你登陆成功！";
		}
		
		return "用户名或密码错误！";
	}
	
	@RequestMapping(value = "/getuserlist",method =RequestMethod.POST)
	@ApiOperation(value = "获取用户列表的post请求",httpMethod = "POST")
	public String getUserList(HttpServletRequest request,@RequestBody User u) {
		Cookie[] cookies =request.getCookies();
		User user=null;
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("login")&&cookie.getValue().equals("true")&&
					u.getUsername().equals("zhangsan")&&u.getPassword().equals("123")) {
				user=new User();
				user.setAge("12");
				user.setName("david");
				user.setSex("male");
				return user.toString();
			}
		}
		return "参数错误";
	}
	
}

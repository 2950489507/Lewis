package com.autotest.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.autotest.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Api(value = "v1",tags = "用户管理接口")
@RequestMapping(value = "user")
public class UserManager {

	@Autowired
	private SqlSessionTemplate template;

	
	@RequestMapping(value = "/getusercount",method = RequestMethod.GET)
	@ApiOperation(value = "获取用户个数",httpMethod = "GET")
	public int getUserCount() {
		return template.selectOne("getUserCount");
	}
	
	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
	@ApiOperation(value = "添加用户接口",httpMethod = "POST")
	public boolean  addUser(HttpServletRequest request,@RequestBody User user) {
		Boolean x = verifyCookies(request);
		int result=0;
		   if(x==true){
	            result = template.insert("addUser",user);
	        }
	        if(result > 0){
	            log.info("添加用户的数量是："+result);
	            return true;
	        }
	        return false;
	}
	
	   @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
	    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
	    public int updateUser(HttpServletRequest request,@RequestBody User user){
	        Boolean x = verifyCookies(request);
	        int i = 0;
	        if(x==true){
	            i = template.update("updateUserInfo",user);
	        }
	        log.info("更新数据的条目数为："+i);
	        return i;
	    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ApiOperation(value = "用户登陆接口",httpMethod = "POST")
	public boolean login(HttpServletResponse response,@RequestBody User user) {
		int result=template.selectOne("login",user);
		Cookie cookie=new Cookie("login","true");
		response.addCookie(cookie);
		log.info("查询到的结果是"+result);
		if(result==1) {
            log.info("登录的用户是："+user.getUsername());
            return true;
		}
		return false;
		
	}
	
	    @ApiOperation(value = "获取用户（列表）信息接口",httpMethod = "POST")
	    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
	    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
	        Boolean x = verifyCookies(request);
	        if(x==true){
	            List<User> users = template.selectList("getUserInfo",user);
	            log.info("getUserInfo 获取到的用户数量是"+users.size());
	            return users;
	        }else {
	            return null;
	        }
	    }
	
    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }

        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                log.info("cookies 验证通过");
                return true;
            }
        }
        return false;
    }
}

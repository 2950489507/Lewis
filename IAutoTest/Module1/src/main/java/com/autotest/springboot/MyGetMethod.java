package com.autotest.springboot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGetMethod {

	@RequestMapping(value = "/getcookies",method = RequestMethod.GET)
	public String getCookies(HttpServletResponse response) {
		
		Cookie cookie = new Cookie("login", "true");
		response.addCookie(cookie);
		return "恭喜你获得cookies成功！";
	}
	
	@RequestMapping(value = "/get/with/cookies",method=RequestMethod.GET)
	public String getWithCookies(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		if(Objects.isNull(cookies)) {
			return "必须携带cookies才能访问";
		}
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
				return "携带cookie访问成功！";
			}
		}
		return "携带cookies才能访问";
	}
	
	//需要携带参数访问的第一种实现方法
	@RequestMapping(value = "/get/with/para",method = RequestMethod.GET)
	public Map<String, Integer> getMyList(@RequestParam Integer start,@RequestParam Integer end){
		
		Map<String, Integer> mylist = new HashMap<String, Integer>();
		mylist.put("洗车",100000);
		mylist.put("冰箱", 3000);
		mylist.put("房子", 400000);	
		return mylist;
	}
	
	//需要携带参数访问的第二种实现方法
	@RequestMapping(value = "/get/with/para/{start}/{end}",method = RequestMethod.GET)
	public Map<String, Integer> getList(@PathVariable Integer start,@PathVariable Integer end){
		
		Map<String, Integer> mylist = new HashMap<String, Integer>();
		mylist.put("汽车",100000);
		mylist.put("冰箱", 3000);
		mylist.put("房子", 400000);	
		return mylist;
	}
}

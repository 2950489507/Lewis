package com.autotest.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "v1",tags = "第一个版本的demo")
@RequestMapping(value = "user")
public class Demo {

	@Autowired
	private SqlSessionTemplate template;
	
	@RequestMapping(value = "/getusercount",method = RequestMethod.GET)
	@ApiOperation(value = "获取用户个数",httpMethod = "GET")
	public int getUserCount() {
		return template.selectOne("getUserCount");
	}
}

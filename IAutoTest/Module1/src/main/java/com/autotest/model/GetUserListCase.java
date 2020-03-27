package com.autotest.model;

import lombok.Data;

@Data
public class GetUserListCase {

	private String username;
	private String age;
	private String sex;
	private String expected; 
}

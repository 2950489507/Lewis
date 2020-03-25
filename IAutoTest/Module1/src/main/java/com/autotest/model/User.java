package com.autotest.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    
    @Override
    public String toString() {
    	
    	return (
    			"{id:"+id+","+
    			"username:"+username+","+
    			"password:"+password+","+
    			"sex:"+sex+","+
    			"age:"+age+","+
    			"permission:"+permission+","+
    			"isDelete:"+isDelete+"}"
    			);
    }
}

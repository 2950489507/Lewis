package com.autotest.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan("com.autotest")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

}

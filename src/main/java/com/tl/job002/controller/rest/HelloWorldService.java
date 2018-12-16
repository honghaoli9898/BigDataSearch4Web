package com.tl.job002.controller.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloWorldService {
	@RequestMapping("/helloworld")
	public String helloworld(){
		return "helloworld，我是restful";
	}
   // 主方法，像一般的Java类一般去右击run as application时候，执行该方法
   public static void main(String[] args) throws Exception {
      SpringApplication.run(HelloWorldService.class, args);
   }
}
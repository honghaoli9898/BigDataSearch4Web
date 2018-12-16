package com.tl.job002.controller.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tl.job002.manager.BdbManager;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.tl" })
public class BdbSearchService {
	@RequestMapping("/search")
	public String search(String mid){
		try{
			return BdbManager.getQueryResult(mid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "一不小心出错了!";
	}
	public static void main(String[] args) {
		SpringApplication.run(BdbSearchService.class, args);
	}
}

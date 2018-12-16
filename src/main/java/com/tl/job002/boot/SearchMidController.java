package com.tl.job002.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchMidController {
	@RequestMapping("/search_mid")
	String index(){
		return "weibo_mid_search";
	}
	public static void main(String[] args) {
		SpringApplication.run(SearchMidController.class, args);
	}
}
